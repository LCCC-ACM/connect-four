package competitors;

import common.Color;
import common.GameConstants;
import common.Player;
import gameEngine.*;

public class MikesPlayer implements Player
{
    private Color color;

    public String getName() {
        return "Mike";
    }

    public Move getMove(Board board) {
        try {
            color = GameManager.getInstance().getPlayerColor(this);
        } catch (UnknownPlayerException e) {
            throw new RuntimeException(e);
        }

        //1. Check if I have 3 in a row and can place a fourth to win
        Integer columnForWin = getColumnForWin(board);
        if (columnForWin != null)
        {
            return new Move(columnForWin);
        }

        //2. Check if my opponent has three in a row and can win the game somehow. If so, block it!
        Integer columnForBlock = getColumnForBlock(board);
        if (columnForBlock != null)
        {
            return new Move(columnForBlock);
        }

        //3. I'm not really that good for now, so just choose a column.
        return new Move(0);
    }

    private Integer getColumnForWin(Board board)
    {
        int numberOfMyColor = 0;

        //Vertical
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            for (int j = 0; j < board.getPiecesInColumn(i); j++)
            {
                if (board.getPieceColor(j, i).equals(this.color))
                {
                    numberOfMyColor++;
                    if (numberOfMyColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                    {
                        return i;
                    }
                }
            }
        }

        //Horizontal
        numberOfMyColor = 0;
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
            {
                if (board.getPieceColor(i, j) != null && board.getPieceColor(i ,j).equals(this.color))
                {
                    numberOfMyColor++;
                    if (numberOfMyColor == GameConstants.SEQ_LENGTH-1)
                    {
                        return j+1;
                    }
                } else
                {
                    numberOfMyColor = 0;
                }
            }
        }

        //DiagonalUp

        return null;
    }

    private Integer getColumnForBlock(Board board)
    {
        return null;
    }
}
