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
        int column = getRandomColumn();
        while (!board.canPlay(column))
        {
            column = getRandomColumn();
        }

        return new Move(column);
    }

    private int getRandomColumn() {
        return (int)(Math.random() * GameConstants.NUM_COLUMNS);
    }

    private Integer getColumnForWin(Board board)
    {
        int numberOfMyColor = 0;

        //Vertical
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            for (int j = 0; j < board.getPiecesInColumn(i); j++)
            {
                if (board.getPieceColor(j, i) != null && board.getPieceColor(j, i).equals(this.color))
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
                    if (numberOfMyColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
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
        numberOfMyColor = 0;
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            try
            {
                if (board.getPieceColor(i, i) != null && board.getPieceColor(i, i).equals(this.color))
                {
                    numberOfMyColor++;
                    if (numberOfMyColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                    {
                        return i+1;
                    }
                } else
                {
                    numberOfMyColor = 0;
                }
            } catch (ArrayIndexOutOfBoundsException e)
            {
                //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                numberOfMyColor = 0;
                continue;
            }
        }

        //DiagonalDown
        numberOfMyColor = 0;
        for (int i = GameConstants.NUM_ROWS-1; i > 0; i--)
        {
            try
            {
                if (board.getPieceColor(i, i) != null && board.getPieceColor(i, i).equals(this.color))
                {
                    numberOfMyColor++;
                    if (numberOfMyColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                    {
                        return i+1;
                    }
                } else
                {
                    numberOfMyColor = 0;
                }
            } catch (ArrayIndexOutOfBoundsException e)
            {
                //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                numberOfMyColor = 0;
                continue;
            }
        }

        return null;
    }

    private Integer getColumnForBlock(Board board)
    {
        int numberOfTheirColor = 0;

        //Vertical
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            for (int j = 0; j < board.getPiecesInColumn(i); j++)
            {
                if (board.getPieceColor(j, i) != null && !board.getPieceColor(j, i).equals(this.color))
                {
                    numberOfTheirColor++;
                    if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                    {
                        return i;
                    }
                }
            }
        }

        //Horizontal
        numberOfTheirColor = 0;
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
            {
                if (board.getPieceColor(i, j) != null && !board.getPieceColor(i ,j).equals(this.color))
                {
                    numberOfTheirColor++;
                    if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                    {
                        return j+1;
                    }
                } else
                {
                    numberOfTheirColor = 0;
                }
            }
        }

        //DiagonalUp
        numberOfTheirColor = 0;
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            try
            {
                if (board.getPieceColor(i, i) != null && !board.getPieceColor(i, i).equals(this.color))
                {
                    numberOfTheirColor++;
                    if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                    {
                        return i+1;
                    }
                } else
                {
                    numberOfTheirColor = 0;
                }
            } catch (ArrayIndexOutOfBoundsException e)
            {
                //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                numberOfTheirColor = 0;
                continue;
            }
        }

        //DiagonalDown
        numberOfTheirColor = 0;
        for (int i = GameConstants.NUM_ROWS-1; i > 0; i--)
        {
            try
            {
                if (board.getPieceColor(i, i) != null && !board.getPieceColor(i, i).equals(this.color))
                {
                    numberOfTheirColor++;
                    if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                    {
                        return i+1;
                    }
                } else
                {
                    numberOfTheirColor = 0;
                }
            } catch (ArrayIndexOutOfBoundsException e)
            {
                //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                numberOfTheirColor = 0;
                continue;
            }
        }

        return null;
    }
}
