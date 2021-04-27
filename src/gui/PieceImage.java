package gui;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PieceImage extends JLabel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int IMAGE_HEIGHT = 50;
	private static final int IMAGE_WIDTH = 50;
	public PieceImage(String path) {
		ImageIcon image = new ImageIcon(path);
		Image img = image.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_AREA_AVERAGING);
		image.setImage(img);
		setIcon(image);
		this.setBounds(0, 100, IMAGE_WIDTH, IMAGE_HEIGHT);
		setHorizontalAlignment(JLabel.CENTER);
	}
}
