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
	private int[] moveList = {4, 4, 3, 3, 3, 2, 2, 1, 0, 0, 0, 0};
}