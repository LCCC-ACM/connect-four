package gameEngine;

class Piece {
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

	public Piece(Color color)
	{
		this.color = color;
	}

	public Color getColor()
	{
		return this.color;
	}

	public Piece copy()
	{
		return new Piece(this.color);
	}
}