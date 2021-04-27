package ai;

import game.Player;

public interface Evaluation {
	int evaluate(Player own, Player enemy);
}
