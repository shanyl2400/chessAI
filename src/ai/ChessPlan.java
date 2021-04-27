package ai;

import chess.Piece;
import chess.Point;

public class ChessPlan {
	private Piece piece;
	private Point allocation;

	public ChessPlan(Piece piece, Point allocation) {
		super();
		this.piece = piece;
		this.allocation = allocation;
	}

	public Piece doPlan() {
		return piece.moveTo(allocation);
	}

	public String toString() {
		return piece.getName() + " from " + piece.getAllocation() + ", to " + allocation;
	}
}
