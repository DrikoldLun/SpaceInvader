package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class P2_5 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double price = scan.nextDouble();
        int dollars = (int) price;
        int cents = (int) ((price-dollars)*100+0.5);
        System.out.printf("%d dollars, %d cents",dollars,cents);
    }
}
