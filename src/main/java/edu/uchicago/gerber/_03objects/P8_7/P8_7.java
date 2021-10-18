package edu.uchicago.gerber._03objects.P8_7;

public class P8_7 {
    public static void main(String[] args) {
        ComboLock comboLock = new ComboLock(3,13,23);
        comboLock.reset();
        comboLock.turnRight(3);
        comboLock.turnRight(10);
        comboLock.turnLeft(30);
        System.out.println(comboLock.open());
    }
}
