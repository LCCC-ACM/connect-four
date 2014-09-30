package competitors;

import common.*;
import gameEngine.*;

public class ExamplePlayer2 extends Player {
	public String getName() {
		return "Player 2";
	}

	public Move getMove(Board board) {
		int column = (int)(Math.random() * GameConstants.NUM_COLUMNS);
		return new Move(column);
	}
}