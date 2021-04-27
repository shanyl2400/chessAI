package chess;

import java.util.ArrayList;
import java.util.List;

//车
public class Chariot extends Piece {
	public static final int PIECE_VALUE = 1;

	public static final String PIECE_RED_NAME = "車";
	public static final String PIECE_BLACK_NAME = "俥";

	public Chariot(Board board, PieceColor color) {
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
		} while (handleNewPoint(candidatedPoint, newPoint));

		// 横向移动
		x = this.allocation.x;
		do {
			newPoint = new Point(x++, this.allocation.y);
		} while (handleNewPoint(candidatedPoint, newPoint));

		// 纵向移动
		do {
			newPoint = new Point(this.allocation.x, y--);
		} while (handleNewPoint(candidatedPoint, newPoint));

		// 纵向移动
		y = this.allocation.y;
		do {
			newPoint = new Point(this.allocation.x, y++);
		} while (handleNewPoint(candidatedPoint, newPoint));

		return candidatedPoint;
	}

	@Override
	public int getCode() {
		// TODO Auto-generated method stub
		return PIECE_VALUE;
	}

	private boolean handleNewPoint(List<Point> candidatedPoint, Point newPoint) {
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
			if (piece.color != this.color) {
				// 地方棋子，吃子
				candidatedPoint.add(newPoint);
			}
			// 己方棋子不能吃
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
