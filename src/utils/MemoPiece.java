package utils;

import chess.Piece;
import chess.Point;

public class MemoPiece {
	private Point lastAllocation;
	private Piece eatPiece;
	private Piece piece;

	public MemoPiece(Piece piece, Point lastAllocation, Piece eatPiece) {
		super();
		this.piece = piece;
		this.lastAllocation = lastAllocation;
		this.eatPiece = eatPiece;
	}

	public void undo() {
		piece.setAllocation(lastAllocation);
		if (eatPiece != null) {
			eatPiece.setDead(false);
		}
	}

	public Piece getEatPiece() {
		return eatPiece;
	}

	public Piece getPiece() {
		return piece;
	}

	public Point getLastAllocation() {
		return lastAllocation;
	}

	public String toString() {
		return getPiece().toString() + " last:" + getLastAllocation();
	}
}
