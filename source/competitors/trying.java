package competitors;

import common.*;
import gameEngine.*;

public class trying implements Player {

	public final int NO_PIECE = 0, BLACK = 1, RED = 2;

	Color myColor;	
	Board board;
	Color internalBoard[][] = new Color[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];

	public String getName() {
		return "trying";
	}

	public Move getMove(Board boardIn) {
		board = boardIn;
		updateInternalBoard();

		getMyColor();

		//System.out.println(findSequence(2, Color.RED));

		int nextMove = findSequence(2, Color.RED);

		if (inBounds(0, nextMove) && board.canPlay(nextMove)) {
			return new Move(nextMove);
		} else {
			return new Move(1);
		}
	}

	public int findSequence(int seqLength, Color color) {
		
		int rowOffsets[] = {1, 0, 1, 1};
		int colOffsets[] = {0, 1, 1, -1};

		for (int row = 0; row < GameConstants.NUM_ROWS; row++) {
			for (int col = 0; col < GameConstants.NUM_COLUMNS; col++) {
				for (int i = 0; i < rowOffsets.length; i++) {

					int rowOffset = rowOffsets[i];
					int colOffset = colOffsets[i];

					boolean seqfound = true;

					for (int j = 0; j < seqLength; j++) {
						int currow = row + j * rowOffset;
						int curcol = col + j * colOffset;

						if (inBounds(currow, curcol) && internalBoard[currow][curcol] == color) {
						} else {
							seqfound = false;
							break;
						}
					}

					if (seqfound) {
						if (i == 0) {
							return col;
						}
						if (i == 1) {
							if (inBounds(row, col - 1)) {
								return col - 1;
							}
							if (inBounds(row, col + seqLength)) {
								return col + seqLength;
							}
						}
						if (i == 2) {
							if (inBounds(row - 1, col - 1)) {
								return col - 1;
							}
							if (inBounds(row + seqLength, col + seqLength)) {
								return col + seqLength;
							}
						}
						if (i == 3) {
							if (inBounds (row - 1, col +1 )) {
								return col + 1;
							}
							if (inBounds(row + seqLength, col - seqLength)) {
								return col - seqLength;
							}
						}
					}

				}
			}
		}

		return -1;	
	}

	public boolean inBounds(int row, int col) {
		return row < GameConstants.NUM_ROWS && row > -1 && col > -1 && col < GameConstants.NUM_COLUMNS;
	}

	public void updateInternalBoard() {
		
		for (int row = 0; row < GameConstants.NUM_ROWS; row++) {
			for (int col = 0; col < GameConstants.NUM_COLUMNS; col++) {
				internalBoard[row][col] = board.getPieceColor(row,col);
			}
		}

	}


	private Color getMyColor() {
		try {
			return GameManager.getInstance().getPlayerColor(this);
		} catch(UnknownPlayerException e) {
			System.out.println("I am not registered.");
		}
		return null;
	}

}
