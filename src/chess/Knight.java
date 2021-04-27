package chess;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
	public static final int PIECE_VALUE = 2;
	public static final String PIECE_RED_NAME = "馬";
	public static final String PIECE_BLACK_NAME = "傌";

	public Knight(Board board, PieceColor color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> getMovablePoints() {
		List<Point> candidatedPoint = new ArrayList<>();
		// 移动方向和马腿
		// 纵向
		Point tempTarget = new Point(1, 2);
		Point tempBarrier = new Point(0, 1);
		for (int i = 0; i < 4; i++) {
			tempTarget.x = -tempTarget.x;
			tempBarrier.x = -tempBarrier.x;
			if (i == 2) {
				tempTarget.y = -tempTarget.y;
				tempBarrier.y = -tempBarrier.y;
			}

			Point target = this.allocation.add(tempTarget);
			Point barrier = this.allocation.add(tempBarrier);
			if (isValid(target, barrier)) {
				candidatedPoint.add(target);
			}
		}

		// 移动方向和马腿
		// 横向
		tempTarget = new Point(2, 1);
		tempBarrier = new Point(1, 0);
		for (int i = 0; i < 4; i++) {
			tempTarget.x = -tempTarget.x;
			tempBarrier.x = -tempBarrier.x;
			if (i == 2) {
				tempTarget.y = -tempTarget.y;
				tempBarrier.y = -tempBarrier.y;
			}

			Point target = this.allocation.add(tempTarget);
			Point barrier = this.allocation.add(tempBarrier);
			if (isValid(target, barrier)) {
				candidatedPoint.add(target);
			}
		}
		return candidatedPoint;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return PIECE_VALUE;
	}

	private boolean isValid(Point point, Point barrier) {
		if (!point.isValid()) {
			return false;
		}

		Piece piece = board.getPiece(barrier);
		// 蹩马腿
		if (piece != null) {
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
