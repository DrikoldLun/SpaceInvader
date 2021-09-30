package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class P3_14 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input year:");
        int year = scan.nextInt();
        boolean isleap;
        if (year%400==0) {
            isleap = true;
        }
        else if (year%100==0) {
            isleap = false;
        }
        else if (year%4==0) {
            isleap = true;
        }
        else{
            isleap = false;
        }

        if (isleap) {
            System.out.printf("%d is a leap year",year);
        }
        else {
            System.out.printf("%d isn't a leap year",year);
        }
    }
}
