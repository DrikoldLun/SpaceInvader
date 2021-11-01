package edu.uchicago.gerber._05dice.P10_9;

import javax.swing.*;
import java.awt.*;
class Threecolorflag extends JComponent {
    public void paintComponent(Graphics g) {
        int[] pos_German = {100,100,90,60};
        Color[] colors_German = {Color.black,Color.red,Color.yellow};
        int[] pos_Hungary = {100,190,90,60};
        Color[] colors_Hungary = {Color.red,Color.white,Color.green};
        drawflag(g,pos_German,colors_German,"German");
        drawflag(g,pos_Hungary,colors_Hungary,"Hungary");
    }

    public void drawflag(Graphics g, int[] pos, Color[] colors, String str) {
        g.drawString(str,pos[0],pos[1]-5);
        g.setColor(colors[0]);
        g.fillRect(pos[0],pos[1],pos[2],pos[3]/3);
        g.setColor(colors[1]);
        g.fillRect(pos[0],pos[1]+pos[3]/3,pos[2],pos[3]/3);
        g.setColor(colors[2]);
        g.fillRect(pos[0],pos[1]+2*pos[3]/3,pos[2],pos[3]/3);
        g.setColor(Color.black);
        g.drawLine(pos[0],pos[1],pos[0]+pos[2],pos[1]);
        g.drawLine(pos[0],pos[1]+pos[3],pos[0]+pos[2],pos[1]+pos[3]);
        g.drawLine(pos[0],pos[1],pos[0],pos[1]+pos[3]);
        g.drawLine(pos[0]+pos[2],pos[1],pos[0]+pos[2],pos[1]+pos[3]);
    }
}
