package gameEngine;

import common.*;

public class GameResult {
	public Player winner;

	@Override
	public String toString()
	{
		return "The winner is " + winner.name + ".";
	}
}