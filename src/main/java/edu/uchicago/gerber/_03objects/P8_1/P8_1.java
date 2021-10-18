package edu.uchicago.gerber._03objects.P8_1;

public class P8_1 {
    public static void main(String[] args) {
        Microwave microwave = new Microwave();
        microwave.start();
        microwave.increasetime();
        microwave.start();
        microwave.switchlevel();
        microwave.start();
        microwave.reset();
        microwave.start();
    }
}
