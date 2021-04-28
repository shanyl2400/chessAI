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
    private int count;

    public AlphaBetaTreePlayer(PieceColor color, Evaluation evaluation, int depth) {
        super(color);
        this.depth = depth;
        this.evaluation = evaluation;
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

        for (ChessPlan plan : plans) {
            plan.doPlan();
            int value = doMinValue(this.depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
            // 回溯
            Memo.getMemo().undoMemo();
            if (value > best) {
                best = value;
                res = plan;
            }
        }
        System.out.println("count:" + count);
        System.out.println("best:" + best);
        return res;
    }

    private int doMaxValue(int depth, int alpha, int beta) {
        if (depth <= 0) {
            count++;
            return evaluation.evaluate(this, enemy);
        }
        List<ChessPlan> plans = generateChessPlans(this);
        for (ChessPlan plan : plans) {
            plan.doPlan();
            int value = doMinValue(depth - 1, alpha, beta);
            // 回溯
            Memo.getMemo().undoMemo();

            if (value > alpha) {
                alpha = value;
            }
            // 剪枝
            if (alpha >= beta) {
                // System.out.println("beta剪枝");
                return beta;
            }
        }
        return alpha;
    }

    private int doMinValue(int depth, int alpha, int beta) {
        if (depth <= 0) {
            count++;
            return evaluation.evaluate(this, enemy);
        }
        List<ChessPlan> plans = generateChessPlans(enemy);
        for (ChessPlan plan : plans) {
            plan.doPlan();
            int value = doMaxValue(depth - 1, alpha, beta);
            // 回溯
            Memo.getMemo().undoMemo();
            if (value < beta) {
                beta = value;
            }
            if (alpha >= beta) {
                // System.out.println("alpha剪枝");
                return alpha;
            }
        }
        return beta;
    }

    private List<ChessPlan> generateChessPlans(Player player) {
        List<ChessPlan> plans = new ArrayList<>();
        for (Piece piece : player.getPieces()) {
            if (!piece.isDead()) {
                List<Point> points = piece.getMovablePoints();
                // System.out.println(piece.getColor() + ":" + piece.getName() + "=>" + points);
                for (Point pt : points) {
                    plans.add(new ChessPlan(piece, pt));
                }
            }
        }
        return plans;
    }
}
