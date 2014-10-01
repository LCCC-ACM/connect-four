package gameEngine;

import competitors.*;
import common.*;

class GameManager {
	private static GameManager singleton;

	private GameManager()
	{

	}

	private final Player player1 = new ExamplePlayer1();
	private final Player player2 = new ExamplePlayer1();

	private final Piece blackPiece = new Piece(Color.BLACK);
	private final Piece redPiece = new Piece(Color.RED);

	private Player nextPlayer = player1;

	public static GameManager getInstance()
	{
		if (singleton == null)
		{
			singleton = new GameManager();
		}

		return singleton;
	}

	public Color getPlayerColor(Player player) throws UnknownPlayerException
	{
		if (player == player1)
		{
			return Color.BLACK;
		}

		if (player == player2)
		{
			return Color.RED;
		}

		throw new UnknownPlayerException();
	}

	protected Player getNextPlayer()
	{
		Player toReturn = nextPlayer;
		nextPlayer = getOtherPlayer(nextPlayer);
		return toReturn;
	}

	private Player getOtherPlayer(Player player)
	{
		if (player == player1)
		{
			return player2;
		}

		return player1;
	}
}