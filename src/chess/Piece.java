package chess;

import java.util.List;

import utils.Memo;
import utils.MemoPiece;

public abstract class Piece {
	public static final int EMPTY_PIECE = 0;

	protected Point allocation;
	private boolean dead;
	protected Board board;
	protected PieceColor color;

	public Piece(Board board, PieceColor color) {
		this.board = board;
		this.color = color;
	}

	public boolean canMove(Point allocation) {
		// TODO Auto-generated method stub
		List<Point> points = getMovablePoints();
		for (Point point : points) {
			if (point.equals(allocation)) {
				return true;
			}
		}
		return false;
	}

	public Point getAllocation() {
		return allocation;
	}

	public PieceColor getColor() {
		return color;
	}

	public abstract List<Point> getMovablePoints();

	public abstract int getCode();

	public abstract String getName();

	public boolean isDead() {
		return dead;
	}

	// 是否在己方
	public boolean isInOwnSide() {
		if (this.allocation.getSphere() == this.color) {
			return true;
		}
		return false;
	}

	public Piece moveTo(Point allocation) {
		if (canMove(allocation)) {
			// 记录之前位置
			Point lastAllocation = this.allocation;

			// 吃子判断
			Piece piece = this.board.getPiece(allocation);

			setAllocation(allocation);
			if (piece != null) {
				// System.out.println("piece:" + this.getName() + " from " + lastAllocation +
				// " to " + allocation + " eat:" + piece.getName() + " piece point " +
				// piece.allocation);
				piece.setDead(true);
			}

			// 记录
			Memo.getMemo().putMemo(new MemoPiece(this, lastAllocation, piece));
			return piece;
		}
		return null;
	}

	public void setAllocation(Point p) {
		allocation = p;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
}
