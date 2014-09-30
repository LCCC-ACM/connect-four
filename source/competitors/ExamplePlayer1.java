package competitors;

import common.*;
import gameEngine.*;

public class ExamplePlayer1 extends Player {
	public String getName() {
		return "Example player";
	}

	public Move getMove(Board board) {
		int column = getRandomColumn();
		while (!board.canPlay(column))
		{
			column = getRandomColumn();
		}

		return new Move(column);
	}

	private int getRandomColumn()
	{
		return (int)(Math.random() * GameConstants.NUM_COLUMNS);
	}
}