package competitors;

import common.*;

public class ExamplePlayer2 extends Player {
	public Move getMove() {
		int column = (int)(Math.random() * GameConstants.NUM_COLUMNS);
		return new Move(column);
	}
}