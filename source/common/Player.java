package common;

import gameEngine.*;

@SuppressWarnings("serial")
public abstract class Player {
	
	public abstract String getName();

	public abstract Move getMove(Board board);
}