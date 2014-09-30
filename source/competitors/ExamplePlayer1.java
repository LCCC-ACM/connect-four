package competitors;

import common.*;

public class ExamplePlayer1 extends Player {
	public String getName() {
		return "Player 1";
	}

	public Move getMove() {
		return new Move(4);
	}
}