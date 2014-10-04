package gameEngine;

import common.*;

@SuppressWarnings("serial")
public class Piece {
	private ConnectFourColor color;

	@Override
	public String toString()
	{
		if (this.color == ConnectFourColor.RED)
		{
			return "R";
		}
		else
		{
			return "B";
		}
	}

	protected Piece(ConnectFourColor color)
	{
		this.color = color;
	}

	protected ConnectFourColor getColor()
	{
		return this.color;
	}

	protected Piece copy()
	{
		return new Piece(this.color);
	}
}