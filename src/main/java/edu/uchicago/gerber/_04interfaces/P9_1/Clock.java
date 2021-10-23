package edu.uchicago.gerber._04interfaces.P9_1;

import java.util.Date;

class Clock {
    protected Date date = new Date();

    public int getHours(){
        return  Integer.parseInt(date.toString().split(" ")[3].split(":")[0]);
    }

    public int getMinutes(){
        return Integer.parseInt(date.toString().split(" ")[3].split(":")[1]);
    }

    public String getTime(){
        return getHours()+":"+getMinutes();
    }
}