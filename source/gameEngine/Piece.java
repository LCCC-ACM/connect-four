package gameEngine;

import common.*;

@SuppressWarnings("serial")
public class Piece {
    private int row;
    private int column;
    private Color color;

    private int movePlayed;
    protected boolean inWinningSet;

    protected Piece(int row, int column, Color color, int movePlayed)
    {
        this.row = row;
        this.column = column;
        this.color = color;
        this.color = color;
    }

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
}