package competitors;

import common.*;
import gameEngine.*;

public class ExamplePlayer1 extends Player {
	public String getName() {
		return "Player 1";
	}

	public Move getMove(Board board) {
		if (board.getPiece(0, 1) == null)
		{
			return new Move(1);
		}
		
		return new Move(2);
	}
}