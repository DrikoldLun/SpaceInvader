package edu.uchicago.gerber._05dice.P10_19;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class BillFrame extends JFrame {
    private double amount;
    private final double TAXRATE = 0.1;
    private final double DEFAULT_FEE = 1;
    private JButton[] dishbuttonstop10;
    private JButton resetbutton, additembutton;
    private HashMap dishpricemap;
    private JLabel otheritemlabel, otheritempricelabel, dishbilllabel, top10dishlabel, smallfeelabel;
    private JTextField otheritemfield, otheritempricefield, smallfeefield;
    private JTextArea dishbillarea, taxfinalamountarea;
    private JPanel panel;

    public BillFrame() {
        amount = 0;
        buildcomponent();
        final int WIDTH = 450;
        final int HEIGHT = 630;
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        setTitle("Bill");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void buildcomponent() {
        panel = new JPanel(null);
        settextfield();
        settextarea();
        assignbuttons();
        add(panel);
    }

    private void settextfield() {
        otheritemlabel = new JLabel("Other item:");
        otheritemlabel.setBounds(20,490,80,20);
        otheritemfield = new JTextField();
        otheritemfield.setBounds(20,520,140,20);
        otheritempricelabel = new JLabel("Price:");
        otheritempricelabel.setBounds(20,550,60,20);
        otheritempricefield = new JTextField("0");
        otheritempricefield.setBounds(50,550,80,20);
        smallfeelabel = new JLabel("Tips");
        smallfeelabel.setBounds(220,460,60,20);
        smallfeefield = new JTextField(String.format("%.2f",DEFAULT_FEE));
        smallfeefield.addActionListener(new Textlistener());
        smallfeefield.setBounds(360,460,60,20);
        panel.add(otheritemlabel);
        panel.add(otheritemfield);
        panel.add(otheritempricelabel);
        panel.add(otheritempricefield);
        panel.add(smallfeelabel);
        panel.add(smallfeefield);
    }

    private void settextarea() {
        dishbilllabel = new JLabel(String.format("%-20s%20s","Dish","Price"));
        dishbilllabel.setBounds(220,20,200,20);
        dishbillarea = new JTextArea(10,10);
        dishbillarea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(dishbillarea);
        jScrollPane.setBounds(220,50,200,400);
        taxfinalamountarea = new JTextArea();
        taxfinalamountarea.setBounds(220,490,200,40);
        updatetaxfinalamount();
        panel.add(dishbilllabel);
        panel.add(jScrollPane);
        panel.add(taxfinalamountarea);
    }

    public void assignbuttons() {
        top10dishlabel = new JLabel("Top10 items");
        top10dishlabel.setBounds(20,20,140,20);
        dishpricemap = new HashMap();
        dishbuttonstop10 = new JButton[10];
        String[] dishnametop10 = {"Fries","Taco","Burger","Ramen","Chicken Sandwish","Steak","Sushi","Coke","Soda","Ice Cream"};
        double[] pricetop10 = {10,11,12,13,14,15,16,2,3,4};
        for(int i=0;i<10;i++) {
            dishbuttonstop10[i] = new JButton(dishnametop10[i]);
            dishpricemap.put(dishbuttonstop10[i],pricetop10[i]);
            dishbuttonstop10[i].setBounds(20,50+i*40,140,20);
            dishbuttonstop10[i].addActionListener(new Clicklistener());
            panel.add(dishbuttonstop10[i]);
        }
        resetbutton = new JButton("Reset");
        resetbutton.addActionListener(new Resetlistener());
        resetbutton.setBounds(50,450,80,20);
        additembutton = new JButton("Add");
        additembutton.addActionListener(new Additemlistener());
        additembutton.setBounds(140,550,40,20);
        panel.add(top10dishlabel);
        panel.add(resetbutton);
        panel.add(additembutton);
    }

    private void updatetaxfinalamount() {
        double smallfee = Double.parseDouble(smallfeefield.getText());
        taxfinalamountarea.setText(String.format("%-20s%20.2f\n%-20s%20.2f\n","Tax",TAXRATE*amount,"Amount",(1+TAXRATE)*amount+smallfee));
    }

    class Clicklistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton selectedbutton = (JButton) e.getSource();
            double dishprice = (double) dishpricemap.get(selectedbutton);
            dishbillarea.append(String.format("%-20s%20.2f",selectedbutton.getText(),dishprice)+"\n");
            amount += dishprice;
            updatetaxfinalamount();
        }
    }

    class Textlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            updatetaxfinalamount();
        }
    }

    class Resetlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            amount = 0;
            smallfeefield.setText(String.format("%.2f",DEFAULT_FEE));
            dishbillarea.setText("");
            updatetaxfinalamount();
        }
    }

    class Additemlistener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String dishname = otheritemfield.getText();
            Double price = Double.parseDouble(otheritempricefield.getText());
            amount += price;
            dishbillarea.append(String.format("%-20s%20.2f\n",dishname,price));
            updatetaxfinalamount();
        }
    }
}