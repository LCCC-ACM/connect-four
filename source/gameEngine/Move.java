package gameEngine;

import common.ConnectFourColor;

@SuppressWarnings("serial")
public class Move {
	public int column;
	private ConnectFourColor playerColor;

	public Move(int column)
	{
		this.column = column;
	}
	
	protected void setPlayerColor(ConnectFourColor playerColor)
	{
		this.playerColor = playerColor;
	}
	
	public ConnectFourColor getPlayerColor()
	{
		if (playerColor == ConnectFourColor.RED)
		{
			return ConnectFourColor.RED;
		}
		
		return ConnectFourColor.BLACK;
	}
}