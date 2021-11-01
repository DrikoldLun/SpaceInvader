package edu.uchicago.gerber._05dice.P10_9;

import javax.swing.*;

class Flagframe extends JFrame {
    public Flagframe() {
        add(new Threecolorflag());
        final int WIDTH = 300;
        final int HEIGHT = 400;
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
