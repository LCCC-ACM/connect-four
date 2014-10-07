package gameEngine;

import java.util.*;
import common.*;

@SuppressWarnings("serial")
public class GameResult {
	private Player winner;
	private LinkedList<Move> moveList;
    private int currentMove = 0;
    private Piece[][] board;
    private Direction winningDirection;

    public Move getNextMove()
    {
        return moveList.get(currentMove++);
    }

    public boolean hasMoreMoves()
    {
        return currentMove < moveList.size();
    }

    public Piece[][] getBoard() { return board; }

	protected GameResult(Player winner, LinkedList<Move> moveList, Piece[][] board)
	{
		this.winner = winner;
		this.moveList = moveList;
        this.board = board;
	}

    public void setWinningDirection(Direction direction) {
        this.winningDirection = direction;
    }

    public Direction getWinningDirection() {
        return this.winningDirection;
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