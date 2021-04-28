package ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import chess.Board;
import chess.Piece;
import chess.PieceColor;
import chess.Point;
import game.Player;
import utils.Memo;

public class AlphaBetaThreadTreePlayer extends Player {
    private Evaluation evaluation;
    private int depth;
    private int count;
    private List<AlphaBetaThread> threads;

    public AlphaBetaThreadTreePlayer(PieceColor color, Evaluation evaluation, int depth) {
        super(color);
        this.depth = depth;
        this.evaluation = evaluation;
        this.threads = new ArrayList<>();
    }

    @Override
    public Piece go() {
        ChessPlan plan = alphaBeta();

        // 打印
        System.out.println(plan);
        // 落子
        return plan.doPlan();
    }

    private ChessPlan alphaBeta() {
        int best = Integer.MIN_VALUE;
        List<ChessPlan> plans = generateChessPlans(this);
        ChessPlan res = null;
        count = 0;
        int id = 1;
        threads.clear();

        Map<Integer, ChessPlan> planMap = new HashMap<>();

        // 刷新Map
        Memo.clearMemoMap();

        // System.out.println("plans:" + plans);
        // System.out.println("plans size:" + plans.size());
        // System.out.println("pieces:" + pieces);
        for (final ChessPlan plan : plans) {
            plan.doPlan();
            Memo.createMemo(id);
            planMap.put(id, plan);

            Board board = plan.getPiece().getBoard();
            try {
                // 克隆board
                Board clonedBoard = (Board) board.clone(id);
                // 克隆选手
                Player clonedPlayer = (Player) this.clone();
                Player clonedEnemy = (Player) enemy.clone();

                // 添加棋子
                clonedPlayer.setPieces(clonedBoard.getPiecesByColor(clonedPlayer.getColor()));
                clonedEnemy.setPieces(clonedBoard.getPiecesByColor(clonedEnemy.getColor()));

                AlphaBetaThread thread = new AlphaBetaThread(id, evaluation, clonedPlayer, clonedEnemy, depth - 1);
                threads.add(thread);
                thread.start();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }

            Memo.getMemo().undoMemo();
            id++;
        }
        id = 0;

        for (AlphaBetaThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (best < thread.getResult()) {
                best = thread.getResult();
                count = count + thread.getCount();
                id = thread.getThreadId();
            }
        }
        System.out.println("count:" + count);
        System.out.println("best:" + best);
        res = planMap.get(id);
        return res;
    }

    static List<ChessPlan> generateChessPlans(Player player) {
        List<ChessPlan> plans = new ArrayList<>();
        for (Piece piece : player.getPieces()) {
            if (!piece.isDead()) {
                List<Point> points = piece.getMovablePoints();
                // System.out.println(piece.getColor() + ":" + piece.getName() + "=>" + points);
                for (Point pt : points) {
                    plans.add(new ChessPlan(piece, new Point(pt.getX(), pt.getY())));
                }
            }
        }
        return plans;
    }
}
