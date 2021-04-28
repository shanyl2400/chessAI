package ai;

import java.util.ArrayList;
import java.util.List;

import chess.Board;
import chess.Piece;
import chess.PieceColor;
import chess.Point;
import game.Player;
import utils.Memo;

public class MinMaxTreePlayer extends Player {
	private Evaluation evaluation;
	private int depth;
	private int count;

	public MinMaxTreePlayer(PieceColor color, Evaluation evaluation, int depth) {
		super(color);
		this.depth = depth;
		this.evaluation = evaluation;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Piece go() {
		// TODO Auto-generated method stub
		ChessPlan plan = minMax();

		// 打印
		System.out.println(plan);
		// 落子
		return plan.doPlan();
	}

	private ChessPlan minMax() {
		int best = Integer.MIN_VALUE;
		count = 0;
		List<ChessPlan> plans = generateChessPlans(this);
		ChessPlan res = null;
		for (ChessPlan plan : plans) {
			plan.doPlan();
			int value = doMin(this.depth - 1);
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

	private int doMax(int depth) {
		int best = Integer.MIN_VALUE;
		int value = 0;
		if (depth <= 0) {
			count++;
			return evaluation.evaluate(this, enemy);
		}

		List<ChessPlan> plans = generateChessPlans(this);
		for (ChessPlan plan : plans) {
			plan.doPlan();
			value = doMin(depth - 1);
			Memo.getMemo().undoMemo();
			if (value > best) {
				best = value;
			}
		}
		return best;
	}

	private int doMin(int depth) {
		int best = Integer.MAX_VALUE;
		int value = 0;
		if (depth <= 0) {
			count++;
			return evaluation.evaluate(this, enemy);
		}

		List<ChessPlan> plans = generateChessPlans(enemy);
		for (ChessPlan plan : plans) {
			plan.doPlan();
			value = doMax(depth - 1);
			Memo.getMemo().undoMemo();
			if (value < best) {
				best = value;
			}
		}
		return best;
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
