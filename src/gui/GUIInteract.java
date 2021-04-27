package gui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import chess.Piece;
import chess.Point;
import game.Game;
import game.Interact;

public class GUIInteract implements Interact {
	private GameFrame frame;
	private Map<Integer, PiecePanel> piecesMap;

	public GUIInteract(GameFrame frame) {
		this.frame = frame;
		piecesMap = new HashMap<Integer, PiecePanel>();
	}

	@Override
	public void newStep(int step, List<Piece> pieces) {
		// TODO Auto-generated method stub
		for (Piece piece : pieces) {
			int code = piece.hashCode();
			if (piecesMap.containsKey(code)) {
				PiecePanel pp = piecesMap.get(code);
				pp.setDead(piece.isDead());
				Point allocation = piece.getAllocation();
				pp.setLocation(GUIMapping.convert(allocation.getX(), allocation.getY()));
			} else {
				PiecePanel pp = new PiecePanel(piece.getName(), piece.getColor());
				Point allocation = piece.getAllocation();
				pp.setLocation(GUIMapping.convert(allocation.getX(), allocation.getY()));
				frame.panelAdd(pp);
				pp.setDead(piece.isDead());
				piecesMap.put(code, pp);
			}
		}
		frame.panelRepaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void gameOver(int winner) {
		// TODO Auto-generated method stub
		String msg = "红方胜";
		if (winner == Game.RESULT_BLACK_WIN) {
			msg = "黑方胜";
		}
		JOptionPane.showMessageDialog(frame, msg, "游戏结束", JOptionPane.INFORMATION_MESSAGE);
	}

	@Override
	public void eatPiece(Piece piece) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(List<Piece> pieces) {
		// TODO Auto-generated method stub
		newStep(0, pieces);
	}

}
