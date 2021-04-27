package ai;

import chess.Boss;
import chess.Cannon;
import chess.Chariot;
import chess.Elephant;
import chess.Guard;
import chess.Knight;
import chess.Piece;
import chess.Soldier;
import game.Player;

public class PieceValueEvaluation implements Evaluation {
	private static final int BOSS_VALUE = 1000;
	private static final int CHARIOT_VALUE = 50;
	private static final int KNIGHT_VALUE = 20;
	private static final int ELEPHANT_VALUE = 8;
	private static final int GUARD_VALUE = 8;
	private static final int CANNON_VALUE = 30;
	private static final int SOLDIER_VALUE = 2;
	private static final int SOLDIER_CROSS_VALUE = 6;

	@Override
	public int evaluate(Player own, Player enemy) {
		// TODO Auto-generated method stub
		int ownValue = 0;
		int enemyValue = 0;
		for (Piece piece : own.getPieces()) {
			if (!piece.isDead())
				ownValue += getPieceValue(piece);
		}
		for (Piece piece : enemy.getPieces()) {
			if (!piece.isDead())
				enemyValue += getPieceValue(piece);
		}
		return ownValue - enemyValue;
	}

	private int getPieceValue(Piece piece) {
		int pieceCode = piece.getCode();
		switch (pieceCode) {
		case Boss.PIECE_VALUE:
			return BOSS_VALUE;
		case Chariot.PIECE_VALUE:
			return CHARIOT_VALUE;
		case Knight.PIECE_VALUE:
			return KNIGHT_VALUE;
		case Elephant.PIECE_VALUE:
			return ELEPHANT_VALUE;
		case Guard.PIECE_VALUE:
			return GUARD_VALUE;
		case Cannon.PIECE_VALUE:
			return CANNON_VALUE;
		case Soldier.PIECE_VALUE:
			if (piece.isInOwnSide()) {
				return SOLDIER_VALUE;
			}
			return SOLDIER_CROSS_VALUE;
		}

		return 0;
	}
}
