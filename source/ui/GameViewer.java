package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import common.GameConstants;
import gameEngine.GameResult;
import gameEngine.Move;

import javax.swing.*;

@SuppressWarnings("serial")
public class GameViewer extends JFrame {
	final static int FRAME_WIDTH = 600;
	final static int FRAME_HEIGHT = 500;
    final static int MOVES_PER_SECOND = 5;
    final static int MILLISECONDS_PER_FRAME = 1000 / MOVES_PER_SECOND;
	
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

        timer = new javax.swing.Timer(MILLISECONDS_PER_FRAME, new ActionListener() {
            @Override
            public void actionPerformed (ActionEvent e) {
                update();
            }
        });
        timer.start();
	}

    private void update() {
        if (!gameResult.hasMoreMoves())
        {
            return;
        }

        makeNextMove();
    }

    private void makeNextMove() {
        Move move = gameResult.getNextMove();
        int row = GameConstants.NUM_ROWS - count[move.column] - 1;
        int column = move.column;
        boardPanel.updatePiece(row, column, move.getPlayerColor());
        count[move.column]++;
    }
}