package edu.uchicago.gerber._05dice.P11_9;

import javax.swing.*;
import java.awt.*;

class Circle extends JComponent {
    private int xleft, ytop, diameter;

    public Circle() {
        this.xleft = 100;
        this.ytop = 100;
        this.diameter = 0;
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.black);
        g.fillOval(xleft,ytop,diameter,diameter);
    }

    public void drawcircle(int[] center, int[] edgept) {
        int r = (int) Math.hypot(center[0]-edgept[0],center[1]-edgept[1]);
        xleft = center[0]-r;
        ytop = center[1]-r;
        diameter = 2*r;
        repaint();
    }
}
