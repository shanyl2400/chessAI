package ai;

import java.util.List;

import game.Player;
import utils.Memo;

public class AlphaBetaThread extends Thread {
    private int id;
    private int count;
    private Evaluation evaluation;
    private Player player;
    private Player enemy;
    private ChessPlan startPlan;
    private int depth;
    private int result;

    public AlphaBetaThread(int id, Evaluation evaluation, Player player, Player enemy, int depth) {
        this.id = id;
        this.count = 0;
        this.evaluation = evaluation;
        this.player = player;
        this.enemy = enemy;
        this.depth = depth;
        this.result = 0;
    }

    @Override
    public void run() {
        result = doMinValue(this.depth - 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private int doMaxValue(int depth, int alpha, int beta) {
        if (depth <= 0) {
            count++;
            return evaluation.evaluate(player, enemy);
        }
        List<ChessPlan> plans = AlphaBetaThreadTreePlayer.generateChessPlans(player);
        for (ChessPlan plan : plans) {
            plan.doPlan();
            int value = doMinValue(depth - 1, alpha, beta);
            // 回溯
            Memo.getMemo(id).undoMemo();

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
            return evaluation.evaluate(player, enemy);
        }
        List<ChessPlan> plans = AlphaBetaThreadTreePlayer.generateChessPlans(enemy);
        for (ChessPlan plan : plans) {
            plan.doPlan();
            int value = doMaxValue(depth - 1, alpha, beta);
            // 回溯
            Memo.getMemo(id).undoMemo();
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

    public int getResult() {
        return result;
    }

    public int getCount() {
        return count;
    }

    public int getThreadId() {
        return id;
    }

    public ChessPlan getStartPlan() {
        return startPlan;
    }
}
