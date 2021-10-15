package edu.uchicago.gerber._03objects.P8_6;

public class Car {
    private double fuellevel = 0;
    private double efficiency;

    public Car(double efficiency) {
        this.efficiency = efficiency;
    }

    public void drive(double distance) {
        fuellevel -= distance/efficiency;
    }

    public void addGas(double gasVolume) {
        fuellevel += gasVolume;
    }

    public double getGasLevel() {
        return fuellevel;
    }
}
