package edu.uchicago.gerber._05dice.P11_9;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Drawcircleframe extends JFrame {
    private boolean isfirstclick;
    private int[] center;
    private Circle circle;

    public Drawcircleframe() {
        isfirstclick = true;
        center = new int[2];
        circle = new Circle();
        circle.addMouseListener(new MousePressListener());
        add(circle);
        final int WIDTH = 300;
        final int HEIGHT = 400;
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class MousePressListener implements MouseListener {

        public void mousePressed(MouseEvent event) {
            int x = event.getX();
            int y = event.getY();
            if (isfirstclick) {
                center[0] = x;
                center[1] = y;
                isfirstclick = false;
            } else {
                int[] edgept = {x,y};
                circle.drawcircle(center,edgept);
                isfirstclick = true;
            }
        }

        public void mouseClicked(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }
}
