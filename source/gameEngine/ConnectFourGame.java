package gameEngine;

import common.*;

public class ConnectFourGame {
	
	private GameManager gameManager =  GameManager.getInstance();
	private Board board = Board.getInstance();
	private int numberOfMoves = 0;
	
	public GameResult play() {
		while (true)
		{
			Player player = gameManager.getNextPlayer();

			Move move = player.getMove(board);

			try 
			{
				board.applyMove(move, gameManager.getPlayerColor(player));
				System.out.println(gameManager.getPlayerColor(player) + " moves in col " + move.column);
				System.out.println(board);
				numberOfMoves++;
			}
			catch (UnknownPlayerException e)
			{
				System.out.println("Unknown player attempted to make a move.");
			}
			catch (InvalidMoveException e)
			{
				System.out.println(player.getName() + " attempted an invalid move.");
			}

			if (board.connectFour())
			{
				return new GameResult(player, numberOfMoves);
			}

			if (board.isFull())
			{
				return new GameResult(null, numberOfMoves);
			}
		}
	}
}