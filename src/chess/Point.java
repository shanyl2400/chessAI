package chess;

public class Point {
	int x;
	int y;

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}

	public Point add(Point p) {
		return new Point(this.x + p.x, this.y + p.y);
	}

	public Point move(PointDirection dir) {
		switch (dir) {
		case Up:
			return new Point(x, y - 1);
		case Down:
			return new Point(x, y + 1);
		case Left:
			return new Point(x - 1, y);
		case Right:
			return new Point(x + 1, y);
		}
		return null;
	}

	public PieceColor getSphere() {
		if (y < 5) {
			return PieceColor.Black;
		}
		return PieceColor.Red;
	}

	public String toString() {
		return "x:" + x + ", y:" + y;
	}

	public boolean inPalace() {
		if ((x >= 3 && x <= 5) && ((y >= 0 && y <= 2) || (y >= 7 && y <= 9))) {
			return true;
		}
		return false;
	}

	public boolean isValid() {
		if (this.x < 0 || this.x >= Board.CHESS_BOARD_WIDTH || this.y < 0 || this.y >= Board.CHESS_BOARD_HEIGHT) {
			return false;
		}
		return true;
	}

	public boolean equals(Point p) {
		if (this.x == p.x && this.y == p.y)
			return true;
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
}
