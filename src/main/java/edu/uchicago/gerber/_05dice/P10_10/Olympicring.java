package edu.uchicago.gerber._05dice.P10_10;

import javax.swing.*;
import java.awt.*;

class Olympicring extends JComponent {
    public void paintComponent(Graphics g) {
        int radius = 40;
        int xleft = 50;
        int ytop = 100;
        int[][] poss = {{xleft,ytop},{xleft+2*radius,ytop},{xleft+4*radius,ytop},{xleft+radius,ytop+radius},{xleft+3*radius,ytop+radius}};
        Color[] colors = {Color.blue,Color.black,Color.red,Color.yellow,Color.green};
        for(int i=0;i<poss.length;i++) {
            drawRing(g,poss[i],2*radius,colors[i]);
        }
    }

    public void drawRing(Graphics g, int[] pos, int diam, Color color) {
        g.setColor(color);
        g.drawOval(pos[0],pos[1],diam,diam);
    }
}