package edu.uchicago.gerber._05dice.pig;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class PigFrame extends JFrame {
    private int score_player, score_computer, score_1turn, turnid;
    private boolean ingame;
    private JLabel playerscorelabel, computerscorelabel, score1turnlabel;
    private JButton holdbutton, continuebutton, restartbutton;
    private JPanel panel;
    private JTextArea resultarea;
    private Random rn;

    public PigFrame() {
        buildcomponents();
        final int WIDTH = 300;
        final int HEIGHT = 330;
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        setTitle("PigGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rn = new Random();
        ingame = true;
        score_1turn = 0;
        score_computer = 0;
        score_player = 0;
        turnid = 1;
    }

    private void buildcomponents() {
        panel = new JPanel(null);
        add(panel);
        buildtext();
        buildbutton();
    }

    private void buildtext() {
        playerscorelabel = new JLabel("Your score: 0");
        playerscorelabel.setBounds(10,10,150,20);
        computerscorelabel = new JLabel("Computer's score: 0");
        computerscorelabel.setBounds(10,40,150,20);
        score1turnlabel = new JLabel("Score for this turn: 0");
        score1turnlabel.setBounds(10,230,150,20);
        resultarea = new JTextArea(10,10);
        resultarea.setEditable(false);
        resultarea.setText("You go first\nTurn 1: Your Turn\n");
        JScrollPane jScrollPane = new JScrollPane(resultarea);
        jScrollPane.setBounds(10,70,250,150);
        panel.add(jScrollPane);
        panel.add(playerscorelabel);
        panel.add(computerscorelabel);
        panel.add(score1turnlabel);
    }

    private void buildbutton() {
        holdbutton = new JButton("Hold");
        holdbutton.setBounds(10,260,40,20);
        holdbutton.addActionListener(new Playerholdlistener());
        continuebutton = new JButton("Roll");
        continuebutton.setBounds(60,260,40,20);
        continuebutton.addActionListener(new Playerrolllistener());
        restartbutton = new JButton("Restart");
        restartbutton.setBounds(110,260,50,20);
        restartbutton.addActionListener(new Restartgamelistener());
        panel.add(holdbutton);
        panel.add(continuebutton);
        panel.add(restartbutton);
    }

    private void computerturn() {
        int numturns = die();
        int roll = 0;
        for (int i=0;i<numturns;i++) {
            roll = die();
            resultarea.append("Computer rolled a "+roll+"\n");
            if (roll==1) {
                score_1turn = 0;
                break;
            } else {
                score_1turn += roll;
                score1turnlabel.setText("Score for this turn: "+score_1turn);
            }
        }
        if (roll!=1) {
            resultarea.append("Computer held\n");
        }
        computer2player();
    }

    private void playerroll() {
        int roll = die();
        resultarea.append("You rolled a "+roll+"\n");
        if (roll == 1) {
            score_1turn = 0;
            player2computer();
        } else {
            score_1turn += roll;
            score1turnlabel.setText("Score for this turn: "+score_1turn);
        }
    }

    private int die() {
        return rn.nextInt(6)+1;
    }

    private void checkwin() {
        if (score_player > 100) {
            resultarea.append("You win!\n");
            ingame = false;
        } else if (score_computer > 100) {
            resultarea.append("Computer win!\n");
            ingame = false;
        }
    }

    private void computer2player() {
        score_computer += score_1turn;
        computerscorelabel.setText("Computer's score: "+score_computer);
        resultarea.append("Computer got totally "+score_1turn+" points in this turn\n");
        score_1turn = 0;
        score1turnlabel.setText("Score for this turn: "+score_1turn);
        checkwin();
        if (ingame) {
            turnid += 1;
            resultarea.append("Turn "+turnid+": Your Turn\n");
        }
    }

    private void player2computer() {
        score_player += score_1turn;
        playerscorelabel.setText("Your score: "+score_player);
        resultarea.append("You got totally "+score_1turn+" points in this turn\n");
        score_1turn = 0;
        score1turnlabel.setText("Score for this turn: "+score_1turn);
        checkwin();
        if (ingame) {
            turnid += 1;
            resultarea.append("Turn "+turnid+": Computer's Turn\n");
            computerturn();
        }
    }

    private void restart() {
        resultarea.setText("You go first\nTurn 1: Your Turn\n");
        turnid = 1;
        score_1turn = 0;
        score_player = 0;
        score_computer = 0;
        ingame = true;
        computerscorelabel.setText("Computer's score: "+score_computer);
        playerscorelabel.setText("Your score: "+score_player);
        score1turnlabel.setText("Score for this turn: "+score_1turn);
    }

    class Playerrolllistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ingame) {
                playerroll();
            }
        }
    }

    class Playerholdlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (ingame) {
                resultarea.append("You held\n");
                player2computer();
            }
        }
    }

    class Restartgamelistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            restart();
        }
    }
}
