package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import chess.PieceColor;

public class PiecePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int PIECE_WIDTH = 40;
	private static final int PIECE_HEIGHT = 40;
	private String name;
	private PieceColor color;
	private boolean dead = false;

	public PiecePanel(String name, PieceColor color) {
		this.setSize(PIECE_WIDTH, PIECE_HEIGHT);
		this.name = name;
		this.color = color;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// 死掉的不画
		if (dead) {
			// super.paintComponent(g);
			return;
		}
		g.setColor(Color.RED);
		if (color == PieceColor.Black) {
			g.setColor(Color.BLACK);
		}
		BasicStroke stokeLine = new BasicStroke(2.0f);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(stokeLine);
		g.fillOval(0, 0, PIECE_WIDTH - 1, PIECE_HEIGHT - 1);

		g.setColor(Color.WHITE);
		g.setFont(new Font("微软雅黑", Font.BOLD, 20));
		g.drawString(name, 10, 26);
	}

	public void setDead(boolean isDead) {
		this.dead = isDead;
	}

}
