package game;

import java.util.ArrayList;
import java.util.List;

import ai.AlphaBetaTreePlayer;
import ai.MinMaxTreePlayer;
import ai.PieceLocationEvaluation;
import ai.PieceValueEvaluation;
import chess.Board;
import chess.Boss;
import chess.Cannon;
import chess.Chariot;
import chess.Knight;
import chess.Piece;
import chess.PieceColor;
import chess.Point;

public class Game {
	public static final int RESULT_RED_WIN = 1;
	public static final int RESULT_BLACK_WIN = 2;
	public static final int RESULT_CONTINUE = 0;

	private Board board;
	private Player players[] = new Player[2]; // 0 is red, 1 is black
	private Interact interact = new NonInteract();

	public Game() {
		board = new Board();
		// TODO: initialize players
		// set enemy
		players[0] = new MinMaxTreePlayer(board, PieceColor.Red, new PieceValueEvaluation(), 4);
		players[1] = new MinMaxTreePlayer(board, PieceColor.Black, new PieceValueEvaluation(), 4);
		players[0].setEnemy(players[1]);
		players[1].setEnemy(players[0]);

		// boolean flag = true;
		// boolean flag2 = true;
		// for (Piece piece : players[0].pieces) {
		// piece.setDead(true);
		// if (piece.getCode() == Boss.PIECE_VALUE) {
		// piece.setDead(false);
		// }
		// if (piece.getCode() == Knight.PIECE_VALUE && flag2) {
		// piece.setDead(false);
		// piece.setAllocation(new Point(5, 5));
		// flag2 = false;
		// }
		// }
		// for (Piece piece : players[1].pieces) {
		// piece.setDead(true);
		// if (piece.getCode() == Boss.PIECE_VALUE) {
		// piece.setDead(false);
		// }
		// if (piece.getCode() == Chariot.PIECE_VALUE && flag) {
		// piece.setDead(false);
		// piece.setAllocation(new Point(4, 4));
		// flag = false;
		// }
		// }
	}

	public static void main(String args[]) {
		Game game = new Game();

		PieceValueEvaluation evl = new PieceValueEvaluation();
		boolean flag = true;
		for (Piece piece : game.players[0].pieces) {
			piece.setDead(true);
			if (piece.getCode() == Boss.PIECE_VALUE) {
				piece.setDead(false);
			}

		}
		for (Piece piece : game.players[1].pieces) {
			piece.setDead(true);
			if (piece.getCode() == Boss.PIECE_VALUE) {
				piece.setDead(false);
			}
			if (piece.getCode() == Chariot.PIECE_VALUE && flag) {
				piece.setDead(false);
				piece.setAllocation(new Point(4, 4));
				flag = false;
			}
		}
		game.players[0].go();
		int value = evl.evaluate(game.players[0], game.players[1]);
		System.out.println("value:" + value);
	}

	public void play() {
		int result = RESULT_CONTINUE;
		int step = 0;
		interact.init(board.getPieces());
		do {
			// 轮流落子
			Piece piece = players[step % 2].go();

			interact.newStep(step + 1, board.getPieces());

			if (piece != null) {
				interact.eatPiece(piece);
			}

			// 检查是否已死
			result = checkBossDead();
			step++;

		} while (result == RESULT_CONTINUE);

		interact.gameOver(result);
		if (result == RESULT_RED_WIN) {
			System.out.println("RED win");
		} else {
			System.out.println("BLACK win");
		}
	}

	public int checkBossDead() {
		for (int i = 0; i < players.length; i++) {
			for (Piece piece : players[i].getPieces()) {
				// 搜索将帅
				if (piece.getCode() == Boss.PIECE_VALUE && piece.isDead()) {
					if (piece.getColor() == PieceColor.Red) {
						// 红方将死，黑方胜
						return RESULT_BLACK_WIN;
					}
					// 黑方将死，红方胜
					return RESULT_RED_WIN;
				}
			}
		}
		return RESULT_CONTINUE;
	}

	public void setInteract(Interact interact) {
		this.interact = interact;
	}

	public Board getBoard() {
		return board;
	}

}
