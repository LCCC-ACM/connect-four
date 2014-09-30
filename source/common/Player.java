package common;

import gameEngine.*;

public abstract class Player {
	
	public abstract String getName();

	public abstract Move getMove(Board board);
}