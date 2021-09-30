package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class E2_4 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int i1 = scan.nextInt();
        int i2 = scan.nextInt();
        System.out.println("The sum is "+(i1+i2));
        System.out.println("The difference is "+(i1-i2));
        System.out.println("The product is "+i1*i2);
        System.out.println("The average is "+(i1+i2)/2.0);
        System.out.println("The distance is "+Math.abs(i1-i2));
        System.out.println("The maximum is "+Math.max(i1,i2));
        System.out.println("The minimum is "+Math.min(i1,i2));
    }
}
