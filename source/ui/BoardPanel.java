package ui;

import java.awt.*;
import common.*;

import javax.swing.JPanel;

public class BoardPanel extends JPanel {
	
	private final int gridHeight = (UIConstants.FRAME_HEIGHT - 30) / GameConstants.NUM_ROWS;
	private final int gridWidth = UIConstants.FRAME_WIDTH / GameConstants.NUM_COLUMNS;
	private final int pieceSize = Math.min(gridHeight, gridWidth);
	
	private common.Color[][] board;
	
	protected void setBoard(common.Color[][] board)
	{
		this.board = board;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

        if (board == null)
        {
            return;
        }

		for (int i = 0; i < GameConstants.NUM_COLUMNS; i++) {
			for (int j = 0; j < GameConstants.NUM_ROWS; j++) {
				if (board[j][i] == common.Color.RED) {
					g.setColor(java.awt.Color.RED);
					g.fillOval(i * gridWidth,
							j * gridHeight,
							pieceSize,
							pieceSize); 
				}
				else if (board[j][i] == common.Color.BLACK) {
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
