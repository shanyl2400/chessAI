package chess;

import java.util.ArrayList;
import java.util.List;

public class Soldier extends Piece {
	public static final int PIECE_VALUE = 7;
	public static final String PIECE_RED_NAME = "兵";
	public static final String PIECE_BLACK_NAME = "卒";

	public Soldier(Board board, PieceColor color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> getMovablePoints() {
		// TODO Auto-generated method stub
		Point point = getForwardPoint();
		List<Point> points = getLeftRightPoint();
		if (points == null) {
			points = new ArrayList<>();
		}
		points.add(point);

		List<Point> candidatedPoint = new ArrayList<>();
		for (Point p : points) {
			Point newPoint = this.allocation.add(p);
			if (isValid(newPoint)) {
				candidatedPoint.add(newPoint);
			}
		}

		return candidatedPoint;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return PIECE_VALUE;
	}

	private Point getForwardPoint() {
		if (this.color == PieceColor.Red) {
			return new Point(0, -1);
		}
		return new Point(0, 1);
	}

	private boolean isValid(Point newPoint) {
		if (!newPoint.isValid()) {
			return false;
		}
		Piece piece = board.getPiece(newPoint);
		if (piece == null) {
			return true;
		} else if (piece.color != this.color) {
			// 吃子
			return true;
		}

		return false;
	}

	private List<Point> getLeftRightPoint() {
		if (this.allocation.getSphere() == this.color) {
			return null;
		}
		List<Point> candidatedPoint = new ArrayList<>();
		candidatedPoint.add(new Point(1, 0));
		candidatedPoint.add(new Point(-1, 0));
		return candidatedPoint;
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
