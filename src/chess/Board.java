package chess;

import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable {
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

	public List<Piece> getPiecesByColor(PieceColor color) {
		List<Piece> result = new ArrayList<Piece>();
		for (Piece piece : pieces) {
			if (piece.color == color) {
				result.add(piece);
			}
		}
		return result;
	}

	public Piece getPiece(Point allocation) {
		for (Piece piece : pieces) {
			if (!piece.isDead() && piece.getAllocation().equals(allocation)) {
				return piece;
			}
		}
		return null;
	}

	public void initPieces(PieceColor color) {
		// 初始化棋子
		int bottomLine = 0;
		int cannonLine = 2;
		int soldierLine = 3;

		if (color == PieceColor.Red) {
			bottomLine = 9;
			cannonLine = 7;
			soldierLine = 6;
		}

		// 车
		Piece chariot1 = new Chariot(this, color);
		chariot1.setAllocation(new Point(0, bottomLine));
		Piece chariot2 = new Chariot(this, color);
		chariot2.setAllocation(new Point(8, bottomLine));
		pieces.add(chariot1);
		pieces.add(chariot2);

		// 马
		Piece knight1 = new Knight(this, color);
		knight1.setAllocation(new Point(1, bottomLine));
		Piece knight2 = new Knight(this, color);
		knight2.setAllocation(new Point(7, bottomLine));
		pieces.add(knight1);
		pieces.add(knight2);

		// 炮
		Piece cannon1 = new Cannon(this, color);
		cannon1.setAllocation(new Point(1, cannonLine));
		Piece cannon2 = new Cannon(this, color);
		cannon2.setAllocation(new Point(7, cannonLine));
		pieces.add(cannon1);
		pieces.add(cannon2);

		// 兵
		for (int i = 0; i < Board.CHESS_BOARD_WIDTH; i += 2) {
			Piece soldier = new Soldier(this, color);
			soldier.setAllocation(new Point(i, soldierLine));
			pieces.add(soldier);
		}
		// 士
		Piece guard1 = new Guard(this, color);
		guard1.setAllocation(new Point(3, bottomLine));
		Piece guard2 = new Guard(this, color);
		guard2.setAllocation(new Point(5, bottomLine));
		pieces.add(guard1);
		pieces.add(guard2);

		// 象
		Piece elephant1 = new Elephant(this, color);
		elephant1.setAllocation(new Point(2, bottomLine));
		Piece elephant2 = new Elephant(this, color);
		elephant2.setAllocation(new Point(6, bottomLine));
		pieces.add(elephant1);
		pieces.add(elephant2);

		// 将
		Piece boss = new Boss(this, color);
		boss.setAllocation(new Point(4, bottomLine));
		pieces.add(boss);

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

	public Object clone(int id) throws CloneNotSupportedException {
		Board board = (Board) super.clone();
		board.pieces = new ArrayList<>();
		for (Piece piece : pieces) {
			Piece p = (Piece) piece.clone(id);
			p.setAllocation(new Point(p.getAllocation().x, p.getAllocation().y));
			p.setBoard(board);
			board.pieces.add(p);
		}

		return (Object) board;
	}
}
