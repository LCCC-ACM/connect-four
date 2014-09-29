package gameEngine;

import common.*;

public class GameResult {
	public Player winner;

	@Override
	public String toString()
	{
		if (winner == null)
		{
			return "Game tied.";
		}

		return "The winner is " + winner.name + ".";
	}
}