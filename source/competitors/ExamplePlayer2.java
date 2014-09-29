package competitors;

import common.*;

public class ExamplePlayer2 extends Player {
	public String getName() {
		return "Player 2";
	}

	public Move getMove() {
		int column = 2;
		return new Move(column);
	}
}