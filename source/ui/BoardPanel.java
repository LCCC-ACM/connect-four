package ui;

import java.awt.*;

import common.*;
import common.Color;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    private Color[][] piece = new Color[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
    private final int gridHeight = (UIConstants.FRAME_HEIGHT - 30) / GameConstants.NUM_ROWS;
    private final int gridWidth = UIConstants.FRAME_WIDTH / GameConstants.NUM_COLUMNS;
    private final int pieceSize = Math.min(gridHeight, gridWidth);

    protected void updatePiece(int row, int column, Color color)
    {
        piece[row][column] = color;
        this.repaint();
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
    }
}