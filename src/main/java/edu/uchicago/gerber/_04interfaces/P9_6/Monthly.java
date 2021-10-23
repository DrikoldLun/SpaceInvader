package edu.uchicago.gerber._04interfaces.P9_6;

public class Monthly extends Appointment{
    public Monthly(String description, int year, int month, int day) {
        super(description, year, month, day);
    }

    @Override
    public boolean occursOn(int year, int month, int day) {
        return day == date[2];
    }
}
