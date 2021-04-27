package chess;

import java.util.ArrayList;
import java.util.List;

public class Cannon extends Piece {
	public static final int PIECE_VALUE = 3;
	public static final String PIECE_RED_NAME = "炮";
	public static final String PIECE_BLACK_NAME = "砲";

	public Cannon(Board board, PieceColor color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> getMovablePoints() {
		List<Point> candidatedPoint = new ArrayList<>();

		int x = this.allocation.x;
		int y = this.allocation.y;
		// 横向移动
		Point newPoint = null;
		do {
			newPoint = new Point(x--, this.allocation.y);
		} while (handleNewPoint(candidatedPoint, newPoint, PointDirection.Left));

		// 横向移动
		x = this.allocation.x;
		do {
			newPoint = new Point(x++, this.allocation.y);
		} while (handleNewPoint(candidatedPoint, newPoint, PointDirection.Right));

		// 纵向移动
		do {
			newPoint = new Point(this.allocation.x, y--);
		} while (handleNewPoint(candidatedPoint, newPoint, PointDirection.Up));

		// 纵向移动
		y = this.allocation.y;
		do {
			newPoint = new Point(this.allocation.x, y++);
		} while (handleNewPoint(candidatedPoint, newPoint, PointDirection.Down));

		return candidatedPoint;
	}

	@Override
	public int getCode() {
		return PIECE_VALUE;
	}

	private boolean handleNewPoint(List<Point> candidatedPoint, Point newPoint, PointDirection dir) {
		if (!newPoint.isValid()) {
			return false;
		}

		if (newPoint.equals(this.allocation)) {
			return true;
		}
		Piece piece = board.getPiece(newPoint);
		if (piece == null) {
			candidatedPoint.add(newPoint);
		} else {
			// 吃子
			Point bombTarget = findBombTarget(newPoint, dir);
			if (bombTarget != null) {
				candidatedPoint.add(bombTarget);
			}
			return false;
		}
		return true;
	}

	private Point nextTarget(Point newPoint, int dir) {
		return null;
	}

	// 吃子判断
	private Point findBombTarget(Point newPoint, PointDirection dir) {
		Point next = newPoint.move(dir);
		while (next.isValid()) {
			// 判断下一个格子
			Piece piece = board.getPiece(next);

			// 格子中有棋子
			if (piece != null) {
				if (piece.color == color) {
					// 己方棋子不能吃
					return null;
				} else {
					return next;
				}
			}

			// 向下搜索
			next = next.move(dir);
		}
		return null;
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
