package game;

import java.util.List;

import chess.Piece;

public interface Interact {
	void init(List<Piece> pieces);
	void newStep(int step, List<Piece> pieces);
	void gameOver(int winner);
	void eatPiece(Piece piece);
}
