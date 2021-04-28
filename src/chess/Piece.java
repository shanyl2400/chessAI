package chess;

import java.util.List;

import utils.Memo;
import utils.MemoPiece;

public abstract class Piece implements Cloneable {
	public static final int EMPTY_PIECE = 0;

	protected Point allocation;
	private boolean dead;
	protected Board board;
	protected PieceColor color;

	private int memoId = 0;

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

	public Board getBoard() {
		return board;
	}

	public int getMemoId() {
		return memoId;
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
				// System.out.println("piece:" + this.getName() + " id:" + this.getMemoId() + "
				// from " + lastAllocation
				// + " to " + allocation + " eat:" + piece.getName() + " piece point " +
				// piece.allocation);
				piece.setDead(true);
			}

			// 记录
			if (memoId <= 0) {
				Memo.getMemo().putMemo(new MemoPiece(this, lastAllocation, piece));
			} else {
				Memo.getMemo(memoId).putMemo(new MemoPiece(this, lastAllocation, piece));
			}

			return piece;
		} else {
			System.out.println("can't move " + this.getName() + ", " + this.getAllocation() + "," + this.isDead());
		}
		return null;
	}

	public void setAllocation(Point p) {
		allocation = p;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public void setMemoId(int memoId) {
		this.memoId = memoId;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public String toString() {
		return getColor() + ":" + getName() + "=>" + getAllocation();
	}

	public Object clone(int id) throws CloneNotSupportedException {
		Piece piece = (Piece) super.clone();
		piece.setMemoId(id);
		return (Object) piece;
	}
}
