package gui;

import java.awt.Point;

public class GUIMapping {
	public static Point convert(int x, int y) {
		int baseX = 50;
		int baseY = 95;

		x = x * 55 + baseX;
		y = y * 60 + baseY;
		return new Point(x, y);
	}
}
