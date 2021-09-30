package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class P3_7 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input the income:");
        double income = scan.nextDouble();
        double tax = Math.min(income,50000)*0.01;
        if (income > 50000) {
            tax += (Math.min(income,75000)-50000)*0.02;
            if (income > 75000) {
                tax += (Math.min(income,100000)-75000)*0.03;
                if (income > 100000) {
                    tax += (Math.min(income,250000)-100000)*0.04;
                    if (income > 250000) {
                        tax += (Math.min(income,500000)-250000)*0.05;
                        if (income > 500000) {
                            tax += (income-500000)*0.06;
                        }
                    }
                }
            }
        }
        System.out.printf("The tax is $%f",tax);
    }
}
