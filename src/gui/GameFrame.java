package gui;

import javax.swing.JFrame;
import chess.PieceColor;

public class GameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BackgroundPanel backgroundPanel;

	public GameFrame() {
		backgroundPanel = new BackgroundPanel("./res/chinese_chess.jpg");

		setBounds(60, 100, 600, 800);
		setVisible(true);
		setTitle("中国象棋AI程序");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		add(backgroundPanel);
		setResizable(false);
		backgroundPanel.setLayout(null);
	}

	public void panelAdd(PiecePanel image) {
		backgroundPanel.add(image);
	}

	public void panelRepaint() {
		backgroundPanel.repaint();
	}
}
