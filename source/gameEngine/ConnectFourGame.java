package gameEngine;

import java.util.*;

import common.*;

public class ConnectFourGame {
	
	private GameManager gameManager =  GameManager.getInstance();
	private Board board = Board.getInstance();
	
	public GameResult play() {
		while (true)
		{
			Player player = gameManager.getNextPlayer();

			Move move = player.getMove(board);

			try 
			{
				gameManager.applyMove(move, player);
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
				return new GameResult(player, gameManager.getMoveList());
			}

			if (board.isFull())
			{
				return new GameResult(null, gameManager.getMoveList());
			}
		}
	}
}