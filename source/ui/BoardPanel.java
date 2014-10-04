package ui;

import java.awt.*;
import java.awt.Color;

import common.*;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {

    private UIPiece[][] piece = new UIPiece[GameConstants.NUM_ROWS][GameConstants.NUM_COLUMNS];
    private LayoutManager layout = new GridLayout(GameConstants.NUM_ROWS, GameConstants.NUM_COLUMNS);

    public BoardPanel()
    {
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setLayout(layout);
        for (int i = 0; i < GameConstants.NUM_ROWS; i++)
        {
            for (int j = 0; j < GameConstants.NUM_COLUMNS; j++)
            {
                piece[i][j] = new UIPiece();
                this.add(piece[i][j]);
            }
        }
    }

    protected void updatePiece(int row, int column, common.Color color)
    {
        java.awt.Color pieceColor = color == common.Color.RED ? java.awt.Color.RED : java.awt.Color.BLACK;
        piece[row][column].setColor(pieceColor);
    }
}