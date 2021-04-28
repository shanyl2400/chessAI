package utils;

import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

public class Memo {
	private Stack<MemoPiece> pieceStack;
	private static Memo memo = new Memo();
	private static Map<Integer, Memo> memoMap = new ConcurrentHashMap<>();

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

	public int size() {
		return pieceStack.size();
	}

	public String toString() {
		return pieceStack.toString();
	}

	public static Memo getMemo() {
		return memo;
	}

	public static void createMemo(int id) {
		Memo memo = new Memo();
		memoMap.put(id, memo);
	}

	public static void clearMemoMap() {
		memoMap = new ConcurrentHashMap<>();
	}

	public static Memo getMemo(int id) {
		return memoMap.get(id);
	}
}
