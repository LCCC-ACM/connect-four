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

        Integer columnForWin = getColumnForWin(board);
        if (columnForWin != null)
        {
            return new Move(columnForWin);
        }

        Integer columnForBlock = getColumnForBlock(board);
        if (columnForBlock != null)
        {
            return new Move(columnForBlock);
        }

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
        //Vertical
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            int numberOfMyColor = 0;
            for (int j = 0; j < board.getPiecesInColumn(i); j++)
            {
                if (board.getPieceColor(j, i) != null && board.getPieceColor(j, i).equals(this.color))
                {
                    numberOfMyColor++;
                    if ((numberOfMyColor == GameConstants.SEQ_LENGTH-1) && board.canPlay(i))
                    {
                        return i;
                    } else
                    {
                        numberOfMyColor = 0;
                    }
                }
            }
        }

        //Horizontal
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            int numberOfMyColor = 0;
            for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
            {
                Color color = board.getPieceColor(i, j);
                if (color == null)
                {
                    boolean checkLeft = true;
                    int leftValue = j-1;
                    while (checkLeft){
                        try
                        {
                            if (board.getPieceColor(i, leftValue) != null && board.getPieceColor(i, leftValue).equals(this.color))
                            {
                                numberOfMyColor++;
                                leftValue--;
                                if (numberOfMyColor == GameConstants.SEQ_LENGTH-1)
                                {
                                    return j;
                                }
                            }
                            else
                            {
                                checkLeft = false;
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            checkLeft = false;
                        }
                    }

                    boolean checkRight = true;
                    int rightValue = j+1;
                    while (checkRight)
                    {
                        try
                        {
                            if (board.getPieceColor(i, rightValue) != null && board.getPieceColor(i, rightValue).equals(this.color))
                            {
                                numberOfMyColor++;
                                leftValue--;
                                if (numberOfMyColor == GameConstants.SEQ_LENGTH-1)
                                {
                                    return j;
                                }
                            }
                            else
                            {
                                checkRight = false;
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            checkRight = false;
                        }
                    }
                }
            }
        }

        //DiagonalUp
        //TODO Finish this and make it awesome
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            int numberOfMyColor = 0;
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
        //TODO Finish this and make it awesome
        for (int i = GameConstants.NUM_ROWS-1; i > 0; i--)
        {
            int numberOfMyColor = 0;
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

        //Vertical
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            int numberOfTheirColor = 0;
            for (int j = 0; j < board.getPiecesInColumn(i); j++)
            {
                if (board.getPieceColor(j, i) != null && !board.getPieceColor(j, i).equals(this.color))
                {
                    numberOfTheirColor++;
                    if ((numberOfTheirColor == GameConstants.SEQ_LENGTH-1) && board.canPlay(i))
                    {
                        return i;
                    }
                } else
                {
                    numberOfTheirColor = 0;
                }
            }
        }

        //Horizontal
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            int numberOfTheirColor = 0;
            for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
            {
                Color color = board.getPieceColor(i, j);
                if (color == null)
                {
                    boolean checkLeft = true;
                    int leftValue = j-1;
                    while (checkLeft){
                        try
                        {
                            if (board.getPieceColor(i, leftValue) != null && !board.getPieceColor(i, leftValue).equals(this.color))
                            {
                                numberOfTheirColor++;
                                leftValue--;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1)
                                {
                                    return j;
                                }
                            }
                            else
                            {
                                checkLeft = false;
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            checkLeft = false;
                        }
                    }

                    boolean checkRight = true;
                    int rightValue = j+1;
                    while (checkRight)
                    {
                        try
                        {
                            if (board.getPieceColor(i, rightValue) != null && !board.getPieceColor(i, rightValue).equals(this.color))
                            {
                                numberOfTheirColor++;
                                leftValue--;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1)
                                {
                                    return j;
                                }
                            }
                            else
                            {
                                checkRight = false;
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            checkRight = false;
                        }
                    }
                }
            }
        }

        //DiagonalUp
        //TODO Finish this and make it awesome
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            int numberOfTheirColor = 0;
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
        //TODO Finish this and make it awesome
        for (int i = GameConstants.NUM_ROWS-1; i > 0; i--)
        {
            int numberOfTheirColor = 0;
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
