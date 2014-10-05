package gameEngine;

import common.*;

@SuppressWarnings("serial")
public class Board {

	private static Board singleton;
	private Piece[][] boardArray;
	private int[] numPieces;

	private Board()
	{
		this.boardArray = new Piece[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
		this.numPieces = new int[GameConstants.NUM_COLUMNS];
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < GameConstants.NUM_ROWS; i++)
		{
            sb.append("|");
			for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
			{
				Piece piece = this.boardArray[i][j];
				if (piece == null)
				{
					sb.append(" |");
				}
				else
				{
					sb.append(piece.toString()).append("|");
				}
			}
			sb.append("\n");
		}

		return sb.toString();
	}

	public Piece getPiece(int row, int column)
	{
        if (boardArray[row][column] == null)
        {
            return null;
        }

		return boardArray[row][column].copy();
	}

	public boolean canPlay(int column)
	{
		if (boardArray[0][column] == null)
		{
			return true;
		}

		return false;
	}

	public int getPiecesInColumn(int column)
	{
		return numPieces[column];
	}

	protected static Board getInstance()
	{
		if (singleton == null)
		{
			singleton = new Board();
		}

		return singleton;
	}

	protected void applyMove(Move move) throws InvalidMoveException
	{
		if (move.column >= GameConstants.NUM_COLUMNS
			|| move.column < 0)
		{
			throw new InvalidMoveException();
		}

		int column = move.column;

		//--Start in the bottom-most row
		int row = GameConstants.NUM_ROWS - 1;
		Piece moveLocation = boardArray[row][column];

		for (int i = 0; i < GameConstants.NUM_ROWS; i++)
		{
			if (boardArray[row - i][column] == null)
			{
				boardArray[row - i][column] = new Piece(move.getPlayerColor());
				numPieces[column]++;
				return;
			}
		}

		throw new InvalidMoveException();
	}

	protected boolean connectFour()
	{
		for (int i = 0; i < GameConstants.NUM_ROWS; i++)
		{
			for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
			{
				if (boardArray[i][j] == null)
				{
					continue;
				}

				if (vertical(i, j)
					|| horizontal(i, j)
					|| diagonalUp(i, j)
					|| diagonalDown(i, j))
				{
					return true;
				}
			}
		}

		return false;
	}

	protected boolean isFull()
	{
		for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
		{
			if (boardArray[0][i] == null)
			{
				return false;
			}
		}

		return true;
	}

	private boolean vertical(int row, int column)
	{
		Color currentColor = boardArray[row][column].getColor();
		if (row > GameConstants.NUM_ROWS - GameConstants.SEQ_LENGTH)
		{
			return false;
		}

		for (int i = 1; i < GameConstants.SEQ_LENGTH; i++)
		{
			if (boardArray[row + i][column].getColor() != currentColor)
			{
				return false;
			}
		}

		return true;
	}

	private boolean horizontal(int row, int column)
	{
		Color currentColor = boardArray[row][column].getColor();
		if (column > GameConstants.NUM_COLUMNS - GameConstants.SEQ_LENGTH)
		{
			return false;
		}

		for (int i = 1; i < GameConstants.SEQ_LENGTH; i++)
		{
			if (boardArray[row][column + i] == null
				|| boardArray[row][column + i].getColor() != currentColor)
			{
				return false;
			}
		}

		return true;
	}

	private boolean diagonalUp(int row, int column)
	{
		Color currentColor = boardArray[row][column].getColor();
		if (column > GameConstants.NUM_COLUMNS - GameConstants.SEQ_LENGTH
			|| row < GameConstants.SEQ_LENGTH - 1)
		{
			return false;
		}

		for (int i = 1; i < GameConstants.SEQ_LENGTH; i++)
		{
			if (boardArray[row - i][column + i] == null
				|| boardArray[row - i][column + i].getColor() != currentColor)
			{
				return false;
			}
		}

		return true;
	}

	private boolean diagonalDown(int row, int column)
	{
		Color currentColor = boardArray[row][column].getColor();
		if (column > GameConstants.NUM_COLUMNS - GameConstants.SEQ_LENGTH
			|| row > GameConstants.NUM_ROWS - GameConstants.SEQ_LENGTH)
		{
			return false;
		}

		for (int i = 1; i < GameConstants.SEQ_LENGTH; i++)
		{
			if (boardArray[row + i][column + i] == null
				|| boardArray[row + i][column + i].getColor() != currentColor)
			{
				return false;
			}
		}

		return true;
	}
}