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

public abstract class Player {
	protected List<Piece> pieces;
	protected PieceColor color;
	protected Board board;
	protected Player enemy;

	public Player(Board board, PieceColor color) {
		pieces = new ArrayList<Piece>();
		this.board = board;
		this.color = color;
		// 初始化棋子
		initPieces(board, color);
		this.board.addPieces(pieces);
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setEnemy(Player enemy) {
		this.enemy = enemy;
	}

	private void initPieces(Board board, PieceColor color) {
		int bottomLine = 0;
		int cannonLine = 2;
		int soldierLine = 3;

		if (color == PieceColor.Red) {
			bottomLine = 9;
			cannonLine = 7;
			soldierLine = 6;
		}

		// 车
		Piece chariot1 = new Chariot(board, color);
		chariot1.setAllocation(new Point(0, bottomLine));
		Piece chariot2 = new Chariot(board, color);
		chariot2.setAllocation(new Point(8, bottomLine));
		pieces.add(chariot1);
		pieces.add(chariot2);

		// 马
		Piece knight1 = new Knight(board, color);
		knight1.setAllocation(new Point(1, bottomLine));
		Piece knight2 = new Knight(board, color);
		knight2.setAllocation(new Point(7, bottomLine));
		pieces.add(knight1);
		pieces.add(knight2);

		// 炮
		Piece cannon1 = new Cannon(board, color);
		cannon1.setAllocation(new Point(1, cannonLine));
		Piece cannon2 = new Cannon(board, color);
		cannon2.setAllocation(new Point(7, cannonLine));
		pieces.add(cannon1);
		pieces.add(cannon2);

		// 兵
		for (int i = 0; i < Board.CHESS_BOARD_WIDTH; i += 2) {
			Piece soldier = new Soldier(board, color);
			soldier.setAllocation(new Point(i, soldierLine));
			pieces.add(soldier);
		}
		// 士
		Piece guard1 = new Guard(board, color);
		guard1.setAllocation(new Point(3, bottomLine));
		Piece guard2 = new Guard(board, color);
		guard2.setAllocation(new Point(5, bottomLine));
		pieces.add(guard1);
		pieces.add(guard2);

		// 象
		Piece elephant1 = new Elephant(board, color);
		elephant1.setAllocation(new Point(2, bottomLine));
		Piece elephant2 = new Elephant(board, color);
		elephant2.setAllocation(new Point(6, bottomLine));
		pieces.add(elephant1);
		pieces.add(elephant2);

		// 将
		Piece boss = new Boss(board, color);
		boss.setAllocation(new Point(4, bottomLine));
		pieces.add(boss);

	}

	public abstract Piece go();
}
