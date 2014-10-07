package competitors;

import common.Color;
import common.GameConstants;
import common.Player;
import gameEngine.Board;
import gameEngine.GameManager;
import gameEngine.Move;
import gameEngine.UnknownPlayerException;

import java.util.PriorityQueue;

public class Superbad implements Player {
    private Color myColor = null;
    private Color theirColor = null;
    private Color[][] myBoard = new Color[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
    private int[] columnCount = new int[GameConstants.NUM_COLUMNS];

    @Override
    public String getName() {
        return "Superbad";
    }

    @Override
    public Move getMove(Board board) {
        if (myColor == null)
        {
            try {
                myColor = GameManager.getInstance().getPlayerColor(this);
            } catch (UnknownPlayerException e) {
                e.printStackTrace();
            }
        }

        if (theirColor == null)
        {
            if (myColor == Color.RED)
            {
                theirColor = Color.BLACK;
            }
            else
            {
                theirColor = Color.BLACK;
            }
        }

        getLastMove(board);

        //--The number of move choices I have cannot be greater than the number
        //-of columns on the board.

        //--For each move, we will rate it based on two factors
        //--1: The length of streak that it will block or augment
        //--2: Whether the streak is mine or my enemies.

        //--I sort move possibilities by the length of streak they can block/augment,
        //-and if there is a tie I will prefer a move that augments my streak
        //-rather than blocks my opponent

        PriorityQueue<MoveOption> moveOptions = new PriorityQueue<MoveOption>();
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            int row = getEmptyRow(i);
            if (row == -1)
            {
                continue;
            }

            moveOptions.add(new MoveOptionBuilder().build(row, i, myBoard, myColor, theirColor));
        }

        int bestMove = moveOptions.poll().column;

        //--If I have time I will implement a check so that I avoid a move that
        //-would allow my opponent to win on the very next turn.

        Move myMove = new Move(bestMove);
        updateBoardWith(myMove);

        return new Move(bestMove);
    }

    private void updateBoardWith(Move myMove) {
        int row = getEmptyRow(myMove.column);
        myBoard[row][myMove.column] = myColor;
        columnCount[myMove.column]++;
    }

    private void getLastMove(Board board) {
        //--Check every column
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++)
        {
            int emptyRow = getEmptyRow(i);
            //--Don't check a column that is full
            if (emptyRow == -1)
            {
                continue;
            }

            Color pieceOnBoard = board.getPieceColor(emptyRow, i);
            //--If I don't have this piece, add it to my board
            if (pieceOnBoard != null
                && myBoard[emptyRow][i] == null)
            {
                myBoard[emptyRow][i] = pieceOnBoard;
                columnCount[i]++;
                return; //--Only one move could have happened since I played.
            }
        }
    }

    private int getEmptyRow(int column)
    {
        return GameConstants.NUM_ROWS - columnCount[column] - 1;
    }
}

class MoveOption implements Comparable<MoveOption> {
    int column;
    int myStreak;
    int theirStreak;

    public MoveOption(int column, int myStreak, int theirStreak)
    {
        this.column = column;
        this.myStreak = myStreak;
        this.theirStreak = theirStreak;
    }

    private int distanceFromCenter()
    {
        return Math.abs((GameConstants.NUM_COLUMNS - 1) / 2 - column);
    }

    @Override
    public int compareTo(MoveOption o) {
        if (Math.max(this.myStreak, this.theirStreak) == Math.max(o.myStreak, o.theirStreak))
        {
            if (o.myStreak == this.myStreak)
            {
                return this.distanceFromCenter() - o.distanceFromCenter();
            }

            return o.myStreak - this.myStreak;
        }

        return Math.max(o.myStreak, o.theirStreak) - Math.max(this.myStreak, this.theirStreak);
    }
}

class MoveOptionBuilder {
    public MoveOption build(int row, int column, Color[][] board, Color myColor, Color theirColor)
    {
        int myLongestStreak = 0;
        int theirLongestStreak = 0;

        int[][] direction = {{0, 1},
                            {1, 0},
                            {1, 1},
                            {1, -1}};
        for (int i = 0; i < 4; i++)
        {
            int myStreak = findStreakInDirection(row, column, direction[i][0], direction[i][1], board, myColor);
            if (myStreak > myLongestStreak)
            {
                myLongestStreak = myStreak;
            }

            int theirStreak = findStreakInDirection(row, column, direction[i][0], direction[i][1], board, theirColor);
            if (theirStreak > theirLongestStreak)
            {
                theirLongestStreak = theirStreak;
            }
        }

        return new MoveOption(column, myLongestStreak, theirLongestStreak);
    }

    private int findStreakInDirection(int row, int col, int dRow, int dCol, Color[][] board, Color color)
    {
        int streak = 0;
        for (int i = 1; i < GameConstants.SEQ_LENGTH - 1; i++)
        {
            int dRowi = dRow * i;
            int dColi = dCol * i;
            if (row + dRowi < 0
                || col + dColi < 0
                || row + dRowi >= GameConstants.NUM_ROWS
                || col + dColi >= GameConstants.NUM_COLUMNS
                || board[row + dRowi][col + dColi] != color
                || board[row + dRowi][col + dColi] == null)
            {
                break;
            }

            streak++;
        }

        for (int i = -1; i < -GameConstants.SEQ_LENGTH + 1; i--)
        {
            int dRowi = dRow * i;
            int dColi = dCol * i;
            if (row + dRowi < 0
                    || col + dColi < 0
                    || row + dRowi >= GameConstants.NUM_ROWS
                    || col + dColi >= GameConstants.NUM_COLUMNS
                    || board[row + dRowi][col + dColi] != color
                    || board[row + dRowi][col + dColi] == null)
            {
                break;
            }

            streak++;
        }

        return streak;
    }
}