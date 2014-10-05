package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventListener;

import common.GameConstants;
import gameEngine.GameResult;
import gameEngine.Move;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameViewer extends JFrame implements ActionListener {
	final static int FRAME_WIDTH = 600;
	final static int FRAME_HEIGHT = 500;
	
	private GameResult gameResult;
	private BoardPanel boardPanel = new BoardPanel();
    private int[] count = new int[GameConstants.NUM_COLUMNS];

    private javax.swing.Timer timer;

    public GameViewer(GameResult result)
	{
		this.gameResult = result;
		add(boardPanel);
		this.setTitle("Connect Four");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

        timer = new javax.swing.Timer(100, this);
        timer.start();
	}

    private void makeNextMove() {
        if (!gameResult.hasMoveMoves())
        {
            timer.stop();
            return;
        }

        Move move = gameResult.getNextMove();
        int row = GameConstants.NUM_ROWS - count[move.column] - 1;
        int column = move.column;
        boardPanel.updatePiece(row, column, move.getPlayerColor());
        count[move.column]++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        makeNextMove();
    }
}