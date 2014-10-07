package ui;

import java.awt.*;
import java.util.List;
import java.util.ArrayList;

import common.*;
import common.Color;
import gameEngine.Direction;
import gameEngine.GameResult;
import gameEngine.Piece;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    private Color[][] piece = new Color[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
    private final int gridHeight = (UIConstants.FRAME_HEIGHT - 30) / GameConstants.NUM_ROWS;
    private final int gridWidth = UIConstants.FRAME_WIDTH / GameConstants.NUM_COLUMNS;
    private final int pieceSize = Math.min(gridHeight, gridWidth);
    private GameResult gameResult;

    protected void updatePiece(int row, int column, Color color)
    {
        piece[row][column] = color;
        this.repaint();
    }

    protected void drawConnectFour(GameResult gameResult)
    {
        this.gameResult = gameResult;
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        for (int i = 0; i < GameConstants.NUM_COLUMNS; i++) {
            for (int j = 0; j < GameConstants.NUM_ROWS; j++) {

                if (piece[j][i] == Color.RED) {
                    g.setColor(java.awt.Color.RED);
                    g.fillOval(i * gridWidth,
                            j * gridHeight,
                            pieceSize,
                            pieceSize);
                }
                else if (piece[j][i] == Color.BLACK) {
                    g.setColor(java.awt.Color.BLACK);
                    g.fillOval(i * gridWidth,
                            j * gridHeight,
                            pieceSize,
                            pieceSize);
                }
            }
        }

        if (gameResult != null)
        {
            //-- Draw the victory line
            //-- find each winning piece
            List<Piece> winningPieces = new ArrayList<Piece>();
            for (int i = 0; i < GameConstants.NUM_COLUMNS; i++) {
                for (int j = 0; j < GameConstants.NUM_ROWS; j++) {
                    if (gameResult.getBoard()[j][i].isInWinningSet()) {
                        winningPieces.add(gameResult.getBoard()[j][i]);
                    }
                }
            }

            if (gameResult.getWinningDirection() == Direction.HORIZONTAL) {
                int row = winningPieces.get(0).getRow();
                int minColumn = Integer.MAX_VALUE;
                int maxColumn = Integer.MIN_VALUE;
                for (Piece winningPiece : winningPieces) {
                    minColumn = winningPiece.getColumn() < minColumn ? winningPiece.getColumn() : minColumn;
                    maxColumn = winningPiece.getColumn() > maxColumn ? winningPiece.getColumn() : maxColumn;
                }
                System.err.println("row: " + row + ", minColumn: " + minColumn + ", maxColumn: " + maxColumn);
            } else if (gameResult.getWinningDirection() == Direction.VERTICAL) {
                int column = winningPieces.get(0).getColumn();
                int minRow = Integer.MAX_VALUE;
                int maxRow = Integer.MIN_VALUE;
                for (Piece winningPiece : winningPieces) {
                    minRow = winningPiece.getRow() < minRow ? winningPiece.getRow() : minRow;
                    maxRow = winningPiece.getRow() > maxRow ? winningPiece.getRow() : maxRow;
                }
                System.err.println("column: " + column + ", minRow: " + minRow + ", maxRow: " + maxRow);
            } else if (gameResult.getWinningDirection() == Direction.DIAGONAL_DOWN) {
                //-- Get the smallest [X,Y] pair and go up and left
            } else {
                //-- Must be DIAGONAL_UP. Get the largest [X] and smallest [Y] and go down and right
            }
        }
    }
}