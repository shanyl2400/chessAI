package process;

import ai.AlphaBetaThreadTreePlayer;
import ai.PieceLocationEvaluation;
import chess.PieceColor;
import game.Game;
import game.Interact;
import gui.GUIInteract;
import gui.GameFrame;

public class GamePlay {
	public static void main(String args[]) {
		Game game = new Game();
		game.setPlayer(new AlphaBetaThreadTreePlayer(PieceColor.Red, new PieceLocationEvaluation(), 7),
				new AlphaBetaThreadTreePlayer(PieceColor.Black, new PieceLocationEvaluation(), 6));
		GameFrame frame = new GameFrame();
		Interact interact = new GUIInteract(frame);

		game.setInteract(interact);
		game.play();
	}
}
