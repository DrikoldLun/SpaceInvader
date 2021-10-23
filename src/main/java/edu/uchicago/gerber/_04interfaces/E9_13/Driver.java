package edu.uchicago.gerber._04interfaces.E9_13;

public class Driver {
    public static void main(String[] args) {
        BetterRectangle betterRectangle = new BetterRectangle();
        betterRectangle.setBounds(0,0,3,4);
        System.out.println(betterRectangle.getPerimeter());
        System.out.println(betterRectangle.getArea());
    }
}
