package edu.uchicago.gerber._04interfaces.P9_1;

public class Driver {
    public static void main(String[] args) {
        WorldClock worldClock = new WorldClock(1); // New York Time
        System.out.println(worldClock.getTime());
    }
}
