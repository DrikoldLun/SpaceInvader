package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class E2_6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        double measureD = scan.nextDouble();
        System.out.printf("%f meters = %f miles = %f feet = %f inches",
                measureD,measureD/1609.344,measureD/0.3048,measureD/0.0254);
    }
}
