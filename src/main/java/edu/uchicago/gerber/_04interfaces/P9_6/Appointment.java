package edu.uchicago.gerber._04interfaces.P9_6;

import java.util.Arrays;

class Appointment {
    String description;
    int[] date = {-1,-1,-1};

    public Appointment(String description, int year, int month, int day) {
        this.description = description;
        this.date[0] = year;
        this.date[1] = month;
        this.date[2] = day;
    }

    public boolean occursOn(int year, int month, int day) {
        return year == date[0] && month == date[1] && day == date[2];
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "description='" + description + '\'' +
                ", date=" + date[0]+"."+date[1]+"."+date[2] +
                '}';
    }
}
