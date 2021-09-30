package edu.uchicago.gerber._01control;
import java.util.Scanner;

public class E3_14 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input month:");
        int month = scan.nextInt();
        System.out.println("Input day:");
        int day = scan.nextInt();
        String season;
        if (month <= 3 && month >= 1){
            season = "Winter";
        }
        else if (month <= 6){
            season = "Spring";
        }
        else if (month <= 9){
            season = "Summer";
        }
        else{
            season = "Fall";
        }
        if (month%3 == 0 && day >= 21){
            if (season.equals("Winter")) {
                season = "Spring";
            }
            else if (season.equals("Spring")) {
                season = "Summer";
            }
            else if (season.equals("Summer")) {
                season = "Fall";
            }
            else {
                season = "Winter";
            }
        }
        System.out.println(season);
    }
}
