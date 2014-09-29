package gameEngine;

import common.*;

public class ConnectFourGame {
	
	private GameManager gameManager =  GameManager.getInstance();
	private Board board = Board.getInstance();
	
	public GameResult play() {
		while (true)
		{
			Player player = gameManager.getNextPlayer();

			Move move = player.getMove();

			try 
			{
				board.applyMove(move, gameManager.getPlayerColor(player));
			}
			catch(UnknownPlayerException e)
			{
				System.out.println("Unknown player attempted to make a move.");
			}

			if (connectFour())
			{
				return new GameResult();
			}

			if (boardFull())
			{
				return new GameResult();
			}
		}
	}

	public boolean connectFour()
	{
		return false;
	}

	public boolean boardFull()
	{
		return false;
	}
}