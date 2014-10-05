package gameEngine;

import common.*;

@SuppressWarnings("serial")
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

    public Color getColor()
    {
        if (this.color == Color.RED)
        {
            return Color.RED;
        }
        else
        {
            return Color.BLACK;
        }
    }

	protected Piece(Color color)
	{
		this.color = color;
	}

	protected Piece copy()
	{
		return new Piece(this.color);
	}
}