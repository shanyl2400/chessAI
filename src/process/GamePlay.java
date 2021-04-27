package process;

import game.Game;
import game.Interact;
import gui.GUIInteract;
import gui.GameFrame;

public class GamePlay {
	public static void main(String args[]) {
		Game game = new Game();
		GameFrame frame = new GameFrame();
		Interact interact = new GUIInteract(frame);
		
		game.setInteract(interact);
		game.play();
	}
}
