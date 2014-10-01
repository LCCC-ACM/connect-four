package competitors;

import common.*;
import gameEngine.*;

public class ExamplePlayer1 extends Player {

	private Color myColor;

	public String getName() {
		return "Example player";
	}

	public Move getMove(Board board) {
		if (myColor == null) {
			myColor = getMyColor();
		}

		int column = getRandomColumn();
		while (!board.canPlay(column))
		{
			column = getRandomColumn();
		}

		return new Move(column);
	}

	private int getRandomColumn() {
		return (int)(Math.random() * GameConstants.NUM_COLUMNS);
	}

	private Color getMyColor() {
		try {
			return GameManager.getInstance().getPlayerColor(this);
		}
		catch(UnknownPlayerException e) {
			System.out.println("I am not registered.");
		}

		return null;
	}
}