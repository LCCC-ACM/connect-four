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
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            if (board.getPiecesInColumn(i) == GameConstants.NUM_COLUMNS)
            {
                //-- It's impossible to place a piece here. Go to the next column.
                continue;
            }
            for (int j = 0; j < GameConstants.NUM_ROWS; j++)
            {
                int numberOfMyColor = 0;
                if (board.getPieceColor(j, i) == null)
                {
                    int largestLength = (GameConstants.NUM_COLUMNS > GameConstants.NUM_ROWS)
                            ? GameConstants.NUM_COLUMNS
                            : GameConstants.NUM_ROWS;
                    boolean moveLeft = true;
                    for (int k = 0; k < largestLength && moveLeft; k++)
                    {
                        //-- Start to the down to the left and then go down and to the left
                        try
                        {
                            if (board.getPieceColor(j-k, i-k) != null && board.getPieceColor(j-k, i-k).equals(this.color))
                            {
                                numberOfMyColor++;
                                if (numberOfMyColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveLeft = false;
                        }
                    }

                    boolean moveRight = true;
                    for (int k = 0; k < largestLength && moveRight; k++)
                    {
                        //-- Move up and to the right
                        try
                        {
                            if (board.getPieceColor(j+k, i+k) != null && !board.getPieceColor(j+k, i+k).equals(this.color))
                            {
                                numberOfMyColor++;
                                if (numberOfMyColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveRight = false;
                        }

                    }

                }
                //-- continue on; the spot is already taken
            }
        }

        //DiagonalDown
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            if (board.getPiecesInColumn(i) == GameConstants.NUM_COLUMNS)
            {
                //-- It's impossible to place a piece here. Go to the next column.
                continue;
            }
            for (int j = 0; j < GameConstants.NUM_ROWS; j++)
            {
                int numberOfTheirColor = 0;
                if (board.getPieceColor(j, i) == null)
                {
                    int largestLength = (GameConstants.NUM_COLUMNS > GameConstants.NUM_ROWS)
                            ? GameConstants.NUM_COLUMNS
                            : GameConstants.NUM_ROWS;
                    boolean moveLeft = true;
                    for (int k = 0; k < largestLength && moveLeft; k++)
                    {
                        //-- Start to the down to the left and then go up and to the left
                        try
                        {
                            if (board.getPieceColor(j+k, i-k) != null && !board.getPieceColor(j+k, i-k).equals(this.color))
                            {
                                numberOfTheirColor++;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveLeft = false;
                        }
                    }

                    boolean moveRight = true;
                    for (int k = 0; k < largestLength && moveRight; k++)
                    {
                        //-- Move down and to the right
                        try
                        {
                            if (board.getPieceColor(j-k, i+k) != null && !board.getPieceColor(j-k, i+k).equals(this.color))
                            {
                                numberOfTheirColor++;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveRight = false;
                        }

                    }

                }
                //-- continue on; the spot is already taken
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
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            if (board.getPiecesInColumn(i) == GameConstants.NUM_COLUMNS)
            {
                //-- It's impossible to place a piece here. Go to the next column.
                continue;
            }
            for (int j = 0; j < GameConstants.NUM_ROWS; j++)
            {
                int numberOfTheirColor = 0;
                if (board.getPieceColor(j, i) == null)
                {
                    int largestLength = (GameConstants.NUM_COLUMNS > GameConstants.NUM_ROWS)
                            ? GameConstants.NUM_COLUMNS
                            : GameConstants.NUM_ROWS;
                    boolean moveLeft = true;
                    for (int k = 0; k < largestLength && moveLeft; k++)
                    {
                        //-- Start to the down to the left and then go down and to the left
                        try
                        {
                            if (board.getPieceColor(j-k, i-k) != null && !board.getPieceColor(j-k, i-k).equals(this.color))
                            {
                                numberOfTheirColor++;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveLeft = false;
                        }
                    }

                    boolean moveRight = true;
                    for (int k = 0; k < largestLength && moveRight; k++)
                    {
                        //-- Move up and to the right
                        try
                        {
                            if (board.getPieceColor(j+k, i+k) != null && !board.getPieceColor(j+k, i+k).equals(this.color))
                            {
                                numberOfTheirColor++;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveRight = false;
                        }

                    }

                }
                //-- continue on; the spot is already taken
            }
        }

        //DiagonalDown
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            if (board.getPiecesInColumn(i) == GameConstants.NUM_COLUMNS)
            {
                //-- It's impossible to place a piece here. Go to the next column.
                continue;
            }
            for (int j = 0; j < GameConstants.NUM_ROWS; j++)
            {
                int numberOfTheirColor = 0;
                if (board.getPieceColor(j, i) == null)
                {
                    int largestLength = (GameConstants.NUM_COLUMNS > GameConstants.NUM_ROWS)
                            ? GameConstants.NUM_COLUMNS
                            : GameConstants.NUM_ROWS;
                    boolean moveLeft = true;
                    for (int k = 0; k < largestLength && moveLeft; k++)
                    {
                        //-- Start to the down to the left and then go up and to the left
                        try
                        {
                            if (board.getPieceColor(j+k, i-k) != null && !board.getPieceColor(j+k, i-k).equals(this.color))
                            {
                                numberOfTheirColor++;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveLeft = false;
                        }
                    }

                    boolean moveRight = true;
                    for (int k = 0; k < largestLength && moveRight; k++)
                    {
                        //-- Move down and to the right
                        try
                        {
                            if (board.getPieceColor(j-k, i+k) != null && !board.getPieceColor(j-k, i+k).equals(this.color))
                            {
                                numberOfTheirColor++;
                                if (numberOfTheirColor == GameConstants.SEQ_LENGTH-1 && board.canPlay(i))
                                {
                                    return i;
                                }
                            }
                        } catch (ArrayIndexOutOfBoundsException e)
                        {
                            //-- This is really bad practice, but this is also a hacky way of realizing we've hit the end of the board with out doing crazy checks.
                            moveRight = false;
                        }

                    }

                }
                //-- continue on; the spot is already taken
            }
        }

        return null;
    }
}
