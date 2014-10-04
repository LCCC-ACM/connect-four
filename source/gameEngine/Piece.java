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

    public ConnectFourColor getColor()
    {
        if (this.color == ConnectFourColor.RED)
        {
            return ConnectFourColor.RED;
        }
        else
        {
            return ConnectFourColor.BLACK;
        }
    }

	protected Piece(ConnectFourColor color)
	{
		this.color = color;
	}

	protected Piece copy()
	{
		return new Piece(this.color);
	}
}