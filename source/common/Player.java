package common;

import gameEngine.*;

@SuppressWarnings("serial")
public interface Player {
	
	String getName();

	Move getMove(Board board);
}