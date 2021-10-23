package edu.uchicago.gerber._04interfaces.P9_6;

import java.util.ArrayList;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        ArrayList<Appointment> arr = new ArrayList() {
            {
                add(new Daily("Daily", 2021, 1, 24));
                add(new Monthly("Monthly", 2021, 2, 23));
                add(new Onetime("Onetime", 2021, 3, 23));
            }
        };
        Scanner scanner = new Scanner(System.in);
        // try: 2021 1 24
        //      2021 2 23
        //      2021 3 23
        System.out.println("Year:");
        int year = scanner.nextInt();
        System.out.println("Month:");
        int month = scanner.nextInt();
        System.out.println("Day:");
        int day = scanner.nextInt();
        for (Appointment appointment : arr) {
            if (appointment.occursOn(year,month,day)){
                System.out.println(appointment.toString());
            }
        }
    }
}
