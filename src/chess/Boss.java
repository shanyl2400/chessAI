package chess;

import java.util.ArrayList;
import java.util.List;

public class Boss extends Piece {
	public static final int PIECE_VALUE = 6;
	public static final String PIECE_RED_NAME = "帅";
	public static final String PIECE_BLACK_NAME = "将";

	private Point[] movePoints = { new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1) };

	public Boss(Board board, PieceColor color) {
		super(board, color);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Point> getMovablePoints() {
		List<Point> candidatedPoint = new ArrayList<>();

		for (int i = 0; i < movePoints.length; i++) {
			Point tempTarget = movePoints[i];
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

	public int getValue() {
		return 100;
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

		// 白脸将
		Point p = nextPoint(point);
		while (p.isValid()) {
			target = board.getPiece(p);
			if (target != null && target.getCode() == Boss.PIECE_VALUE && target.getColor() != color) {
				return false;
			}

			p = nextPoint(p);
		}

		return true;
	}

	private Point nextPoint(Point p) {
		if (color == PieceColor.Black) {
			return new Point(p.x, p.y + 1);
		}
		return new Point(p.x, p.y - 1);
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
