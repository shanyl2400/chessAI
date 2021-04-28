package game;

import java.util.ArrayList;
import java.util.List;

import chess.Board;
import chess.Boss;
import chess.Cannon;
import chess.Chariot;
import chess.Elephant;
import chess.Guard;
import chess.Knight;
import chess.Piece;
import chess.PieceColor;
import chess.Point;
import chess.Soldier;

public abstract class Player implements Cloneable {
	protected List<Piece> pieces;
	protected PieceColor color;
	protected Player enemy;

	public Player(PieceColor color) {
		pieces = new ArrayList<Piece>();
		this.color = color;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}

	public Player getEnemy() {
		return enemy;
	}

	public PieceColor getColor() {
		return color;
	}

	public Object clone() throws CloneNotSupportedException {
		return (Player) super.clone();
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}

	public abstract Piece go();
}
