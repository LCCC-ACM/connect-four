package competitors;

import common.*;

public class ExamplePlayer2 extends Player {
	public String getName() {
		return "Player 2";
	}

	public Move getMove() {
		int column = 10;
		return new Move(column);
	}
}