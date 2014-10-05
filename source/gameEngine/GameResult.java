package gameEngine;

import java.util.*;
import common.*;

@SuppressWarnings("serial")
public class GameResult {
	private Player winner;
	private LinkedList<Move> moveList;
    private int currentMove = 0;

    public Move getNextMove()
    {
        return moveList.get(currentMove++);
    }

    public boolean hasMoreMoves()
    {
        return currentMove < moveList.size();
    }

	protected GameResult(Player winner, LinkedList<Move> moveList)
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