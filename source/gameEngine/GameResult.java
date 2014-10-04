package gameEngine;

import java.util.*;
import common.*;

@SuppressWarnings("serial")
public class GameResult {
	private Player winner;
	public Queue<Move> moveList;

	protected GameResult(Player winner, Queue<Move> moveList)
	{
		this.winner = winner;
		this.moveList = moveList;
	}

	@Override
	public String toString()
	{
		if (winner == null)
		{
			return "Game tied.";
		}

		return "In this epic game lasting " + moveList.size() + " moves,\n"
				+ "the winner was " + winner.getName() + ".\n";
	}
}