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
				System.out.println(gameManager.getPlayerColor(player) + " moves in col " + move.column);
			}
			catch (UnknownPlayerException e)
			{
				System.out.println("Unknown player attempted to make a move.");
			}
			catch (InvalidMoveException e)
			{
				System.out.println("Invalid move was attempted.");
			}

			if (board.connectFour())
			{
				return new GameResult(player);
			}

			if (board.isFull())
			{
				return new GameResult(null);
			}

			System.out.println(board);
		}
	}
}