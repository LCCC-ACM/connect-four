package gameEngine;

import competitors.*;
import common.*;

class GameManager {
	private static GameManager singleton;

	private GameManager()
	{

	}

	private final Player player1 = new ExamplePlayer1();
	private final Player player2 = new ExamplePlayer2();

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

	public Player getNextPlayer()
	{
		Player toReturn = nextPlayer;
		nextPlayer = getOtherPlayer(nextPlayer);
		return toReturn;
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

	public Piece getPiece(Player player) throws UnknownPlayerException
	{
		if (player == player1)
		{
			return blackPiece;
		}
		else if (player == player2)
		{
			return redPiece;
		}

		throw new UnknownPlayerException();
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