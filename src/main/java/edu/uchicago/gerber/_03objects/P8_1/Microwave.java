package edu.uchicago.gerber._03objects.P8_1;

public class Microwave {
    private int time = 0;
    private int level = 1;

    public void increasetime(){
        this.time += 30;
    }

    public void switchlevel(){
        if (this.level==1) {
            this.level = 2;
        } else {
            this.level = 1;
        }
    }

    public void reset(){
        this.time = 0;
        this.level = 1;
    }

    public void start(){
        System.out.printf("Cooking for %d seconds at level %d\n", this.time, this.level);
    }
}
