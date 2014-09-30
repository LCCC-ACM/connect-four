package competitors;

import common.*;

public class ExamplePlayer2 extends Player {
	public String getName() {
		return "Player 2";
	}

	public Move getMove() {
		return new Move(moveList[currentMove++]);
	}

	private int currentMove = 0;
	private int[] moveList = {2, 2, 3, 3, 3, 4, 4, 5, 6, 6, 6, 6};
}