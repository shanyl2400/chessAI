package utils;

import java.util.Stack;

public class Memo {
	private Stack<MemoPiece> pieceStack;
	private static Memo memo = new Memo();

	private Memo() {
		pieceStack = new Stack<>();
	}

	public void putMemo(MemoPiece piece) {
		pieceStack.push(piece);
	}

	public boolean undoMemo() {
		MemoPiece piece = pieceStack.pop();
		if (piece == null) {
			return false;
		}
		piece.undo();
		return true;
	}

	public static Memo getMemo() {
		return memo;
	}
}
