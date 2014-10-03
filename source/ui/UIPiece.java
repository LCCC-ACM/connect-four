package ui;

import javax.swing.*;
import java.awt.*;

class UIPiece extends JPanel {

    private java.awt.Color color;

    public void setColor(Color pieceColor) {
        this.color = pieceColor;
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (color != null)
        {
            g.setColor(color);
            g.fillOval(0, 0, 30, 30);
        }
    }
}