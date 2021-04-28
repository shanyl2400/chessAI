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

public class PieceLocationEvaluation implements Evaluation {
	private static final int BOSS_VALUE = 1000;
	private static final int CHARIOT_VALUE = 50;
	private static final int KNIGHT_VALUE = 20;
	private static final int KNIGHT_ENDING_VALUE = 25;
	private static final int ELEPHANT_VALUE = 8;
	private static final int GUARD_VALUE = 8;
	private static final int CANNON_VALUE = 30;
	private static final int CANNON_ENDING_VALUE = 20;
	private static final int SOLDIER_VALUE = 1;
	private static final int SOLDIER_CROSS_VALUE = 5;

	@Override
	public int evaluate(Player own, Player enemy) {
		// TODO Auto-generated method stub
		int ownValue = 0;
		int enemyValue = 0;

		int pieceNum = calculatePiecesNum(own, enemy);

		for (Piece piece : own.getPieces()) {
			if (piece.isDead())
				continue;
			ownValue += getPieceValue(piece, pieceNum);
			if (!piece.isInOwnSide()) {
				// 打入敌军内部
				ownValue += 4;
			}
		}
		for (Piece piece : enemy.getPieces()) {
			if (piece.isDead())
				continue;
			enemyValue += getPieceValue(piece, pieceNum);
			if (!piece.isInOwnSide()) {
				ownValue += 1;
			}
		}
		// System.out.println("ownValue:" + ownValue);
		// System.out.println("enemyValue:" + enemyValue);
		return ownValue - enemyValue;
	}

	private int calculatePiecesNum(Player own, Player enemy) {
		int num = 0;
		for (Piece piece : own.getPieces()) {
			if (piece.isDead())
				continue;
			num++;
		}
		for (Piece piece : enemy.getPieces()) {
			if (piece.isDead())
				continue;
			num++;
		}
		return num;
	}

	private int getPieceValue(Piece piece, int piecesNum) {
		int pieceCode = piece.getCode();
		switch (pieceCode) {
		case Boss.PIECE_VALUE:
			return BOSS_VALUE;
		case Chariot.PIECE_VALUE:
			return CHARIOT_VALUE;
		case Knight.PIECE_VALUE:
			// 残局加分
			if (piecesNum < 16) {
				return KNIGHT_ENDING_VALUE;
			}
			return KNIGHT_VALUE;
		case Elephant.PIECE_VALUE:
			return ELEPHANT_VALUE;
		case Guard.PIECE_VALUE:
			return GUARD_VALUE;
		case Cannon.PIECE_VALUE:
			// 残局减分
			if (piecesNum < 16) {
				return CANNON_ENDING_VALUE;
			}
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
