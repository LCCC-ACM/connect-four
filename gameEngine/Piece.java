package gameEngine;

class Piece {
	private Color color;

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