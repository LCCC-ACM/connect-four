package gameEngine;

import common.*;

@SuppressWarnings("serial")
public class Piece {
    private int row;
    private int column;
    private Color color;

    private int movePlayed;
    private boolean inWinningSet;

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

    public int getRow() { return row; }
    public int getColumn() {return column; }
    public boolean isInWinningSet() { return inWinningSet; }
    public void setInWinningSet(boolean inWinningSet) {
        this.inWinningSet = inWinningSet;
    }
}