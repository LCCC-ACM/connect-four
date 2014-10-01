package gameEngine;

import common.*;

public class GameResult {
	private Player winner;
	private int numberOfMoves;

	protected GameResult(Player winner, int numberOfMoves)
	{
		this.winner = winner;
		this.numberOfMoves = numberOfMoves;
	}

	@Override
	public String toString()
	{
		if (winner == null)
		{
			return "Game tied.";
		}

		return "In this epic game lasting " + numberOfMoves + " moves,\n"
				+ "the winner was " + winner.getName() + ".\n";
	}
}