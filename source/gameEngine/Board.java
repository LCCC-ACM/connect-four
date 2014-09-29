package gameEngine;

import common.*;

class Board {

	private static Board singleton;
	protected Piece[][] boardArray;

	private Board()
	{
		this.boardArray = new Piece[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < GameConstants.NUM_ROWS; i++)
		{
			for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
			{
				Piece piece = this.boardArray[i][j];
				if (piece == null)
				{
					sb.append(" ");
				}
				else
				{
					sb.append(piece.toString());
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public static Board getInstance()
	{
		if (singleton == null)
		{
			singleton = new Board();
		}

		return singleton;
	}

	public Piece getPiece(int row, int column) {
		return singleton.boardArray[row][column].copy();
	}

	protected void applyMove(Move move, Color color) throws InvalidMoveException
	{
		int column = move.column;
		int row = GameConstants.NUM_ROWS - 1;
		Piece moveLocation = singleton.boardArray[row][column];

		for (int i = 0; i < GameConstants.NUM_ROWS; i++)
		{
			if (boardArray[row - i][column] == null)
			{
				boardArray[row - i][column] = new Piece(color);
				return;
			}
		}

		throw new InvalidMoveException();
	}

	protected boolean connectFour()
	{
		return false;
	}

	protected boolean isFull()
	{
		for (int i = 0; i < GameConstants.NUM_ROWS; i++)
		{
			for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
			{
				if (boardArray[i][j] == null)
				{
					return false;
				}
			}
		}

		return true;
	}
}