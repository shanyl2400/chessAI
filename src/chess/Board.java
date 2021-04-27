package chess;

import java.util.ArrayList;
import java.util.List;

public class Board {
	public static final int CHESS_BOARD_WIDTH = 9;
	public static final int CHESS_BOARD_HEIGHT = 10;
	private int map[][];
	private List<Piece> pieces;

	public Board() {
		map = new int[CHESS_BOARD_HEIGHT][CHESS_BOARD_WIDTH];
		pieces = new ArrayList<Piece>();
	}

	public void addPieces(List<Piece> pieces) {
		this.pieces.addAll(pieces);
	}

	public Piece getPiece(Point allocation) {
		for (Piece piece : pieces) {
			if (!piece.isDead() && piece.getAllocation().equals(allocation)) {
				return piece;
			}
		}
		return null;
	}

	// Unused
	public List<Piece> removeDeadPieces() {
		List<Piece> pendingDeletePieces = new ArrayList<Piece>();
		for (Piece piece : pieces) {
			if (piece.isDead()) {
				pendingDeletePieces.add(piece);
			}
		}
		// 删除死掉的棋子
		for (Piece piece : pendingDeletePieces) {
			pieces.remove(piece);
		}
		return pendingDeletePieces;
	}

	public int[][] renderMap() {
		for (int i = 0; i < CHESS_BOARD_HEIGHT; i++) {
			for (int j = 0; j < CHESS_BOARD_WIDTH; j++) {
				map[i][j] = Piece.EMPTY_PIECE;
			}
		}

		for (Piece piece : pieces) {
			if (!piece.isDead()) {
				map[piece.allocation.y][piece.allocation.x] = piece.getCode();
			}
		}

		return map;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

}
