package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class E4_17 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        while (num!=0) {
            System.out.println(num%2);
            num /= 2;
        }
    }
}
