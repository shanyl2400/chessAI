package chess;

import java.util.ArrayList;
import java.util.List;

public class Guard extends Piece {
	public static final int PIECE_VALUE = 5;

	public static final String PIECE_RED_NAME = "士";
	public static final String PIECE_BLACK_NAME = "仕";

	public Guard(Board board, PieceColor color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> getMovablePoints() {
		List<Point> candidatedPoint = new ArrayList<>();
		Point tempTarget = new Point(1, 1);
		for (int i = 0; i < 4; i++) {
			tempTarget.x = -tempTarget.x;
			if (i == 2) {
				tempTarget.y = -tempTarget.y;
			}

			Point target = this.allocation.add(tempTarget);
			if (isValid(target)) {
				candidatedPoint.add(target);
			}
		}

		return candidatedPoint;
	}

	@Override
	public int getCode() {
		return PIECE_VALUE;
	}

	private boolean isValid(Point point) {
		if (!point.isValid()) {
			return false;
		}
		// 只能在宫内移动
		if (!point.inPalace()) {
			return false;
		}
		// 只能在己方势力范围
		if (point.getSphere() != color) {
			return false;
		}

		Piece target = board.getPiece(point);
		// 吃子
		if (target != null && target.color == this.color) {
			return false;
		}
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		if (this.color == PieceColor.Red) {
			return PIECE_RED_NAME;
		}
		return PIECE_BLACK_NAME;
	}
}
