package gameEngine;

import common.*;

public class Piece {
	private Color color;

	@Override
	public String toString()
	{
		if (this.color == Color.RED)
		{
			return "R";
		}
		else
		{
			return "B";
		}
	}

	protected Piece(Color color)
	{
		this.color = color;
	}

	protected Color getColor()
	{
		return this.color;
	}

	protected Piece copy()
	{
		return new Piece(this.color);
	}
}