package competitors;

import common.*;

public class ExamplePlayer1 extends Player {
	public String getName() {
		return "Player 1";
	}

	public Move getMove() {
		int column = 1;
		return new Move(column);
	}
}