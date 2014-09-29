package gameEngine;

import common.*;

public class GameResult {
	public Player winner;

	public GameResult(Player winner)
	{
		this.winner = winner;
	}

	@Override
	public String toString()
	{
		if (winner == null)
		{
			return "Game tied.";
		}

		return "The winner is " + winner.getName() + ".";
	}
}