package edu.uchicago.gerber._05dice.P10_10;

import javax.swing.*;

class Ringframe extends JFrame {
    public Ringframe() {
        add(new Olympicring());
        final int WIDTH = 300;
        final int HEIGHT = 300;
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
