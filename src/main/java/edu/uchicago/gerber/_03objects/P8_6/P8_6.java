package edu.uchicago.gerber._03objects.P8_6;

public class P8_6 {
    public static void main(String[] args) {
        Car car = new Car(3.5);
        car.addGas(2);
        car.drive(3.5);
        System.out.println(car.getGasLevel());
    }
}
