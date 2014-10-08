package ui;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import common.*;
import common.Color;
import gameEngine.GameResult;
import gameEngine.Piece;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    private Color[][] piece = new Color[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
    private final int gridHeight = Double.valueOf(((double)UIConstants.FRAME_HEIGHT * 0.94) / GameConstants.NUM_ROWS).intValue();
    private final int gridWidth = UIConstants.FRAME_WIDTH / GameConstants.NUM_COLUMNS;
    private final int pieceSize = Math.min(gridHeight, gridWidth);
    private GameResult gameResult;

    protected void updatePiece(int row, int column, Color color)
    {
        piece[row][column] = color;
        this.repaint();
    }

    protected void setGameResult(GameResult gameResult)
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

        if (gameResult != null && gameResult.getWinner() != null && !gameResult.hasMoreMoves())
        {
            //-- Draw the victory line
            //-- find each winning piece
            List<Piece> winningPieces = new ArrayList<Piece>();
            for (int i = 0; i < GameConstants.NUM_COLUMNS; i++) {
                for (int j = 0; j < GameConstants.NUM_ROWS; j++) {
                    if (gameResult.getBoard()[j][i] != null && gameResult.getBoard()[j][i].isInWinningSet()) {
                        winningPieces.add(gameResult.getBoard()[j][i]);
                    }
                }
            }

            for (int i = 0; i < winningPieces.size()-1; i++) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setStroke(new BasicStroke(3));
                g2.setColor(java.awt.Color.YELLOW);
                g2.drawLine((winningPieces.get(i).getColumn() * gridWidth) + (pieceSize/2),
                        (winningPieces.get(i).getRow() * gridHeight)+ (pieceSize/2),
                        (winningPieces.get(i+1).getColumn() * gridWidth)+ (pieceSize/2),
                        (winningPieces.get(i+1).getRow() * gridHeight)+ (pieceSize/2));
            }
        }
    }
}