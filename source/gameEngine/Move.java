package gameEngine;

import common.Color;

@SuppressWarnings("serial")
public class Move {
	public int column;
	private Color playerColor;

	public Move(int column)
	{
		this.column = column;
	}
	
	protected void setPlayerColor(Color playerColor)
	{
		this.playerColor = playerColor;
	}
	
	public Color getPlayerColor()
	{
		if (playerColor == Color.RED)
		{
			return Color.RED;
		}
		
		return Color.BLACK;
	}
}