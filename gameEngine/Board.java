package gameEngine;

import common.*;

class Board {

	private static Board singleton;
	protected Piece[][] piece;

	private Board()
	{
		this.piece = new Piece[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
	}

	public static Board getInstance()
	{
		if (singleton == null)
		{
			return new Board();
		}

		return singleton;
	}

	public Piece getPiece(int row, int column) {
		return singleton.piece[row][column].copy();
	}

	protected void applyMove(Move move, Color color)
	{
		int column = move.column;
		int row = GameConstants.NUM_ROWS - 1;
		Piece moveLocation = singleton.piece[row][column];

		while (moveLocation != null)
		{
			row++;
			moveLocation = piece[row][column];
		}

		moveLocation = new Piece(color);
	}
}