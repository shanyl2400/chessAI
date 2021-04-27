package ai;

import java.util.ArrayList;
import java.util.List;

import chess.Board;
import chess.Piece;
import chess.PieceColor;
import chess.Point;
import game.Player;
import utils.Memo;

public class AlphaBetaTreePlayer extends Player {
    private Evaluation evaluation;
    private int depth;

    public AlphaBetaTreePlayer(Board board, PieceColor color, Evaluation evaluation, int depth) {
        super(board, color);
        this.depth = depth;
        this.evaluation = evaluation;
    }

    @Override
    public Piece go() {
        // TODO Auto-generated method stub
        ChessPlan plan = alphaBeta();

        // 打印
        System.out.println(plan);
        // 落子
        return plan.doPlan();
    }

    private ChessPlan alphaBeta() {
        int best = Integer.MIN_VALUE;
        List<ChessPlan> plans = generateChessPlans();
        ChessPlan res = null;

        for (ChessPlan plan : plans) {
            plan.doPlan();
            int value = -doAlphaBeta(this.depth - 1, 0, 0);
            // 回溯
            Memo.getMemo().undoMemo();
            if (value > best) {
                best = value;
                res = plan;
            }
        }
        System.out.println("best:" + best);
        return res;
    }

    private int doAlphaBeta(int depth, int alpha, int beta) {
        if (depth <= 0) {
            return evaluation.evaluate(this, enemy);
        }
        List<ChessPlan> plans = generateChessPlans();
        for (ChessPlan plan : plans) {
            plan.doPlan();
            int value = -doAlphaBeta(depth - 1, -beta, -alpha);
            // 回溯
            Memo.getMemo().undoMemo();
            if (value > alpha) {
                alpha = value;
            }
            if (value >= beta) {
                return beta;
            }
        }
        return alpha;
    }

    private List<ChessPlan> generateChessPlans() {
        List<ChessPlan> plans = new ArrayList<>();
        for (Piece piece : pieces) {
            if (!piece.isDead()) {
                List<Point> points = piece.getMovablePoints();
                for (Point pt : points) {
                    plans.add(new ChessPlan(piece, pt));
                }
            }
        }
        return plans;
    }
}
