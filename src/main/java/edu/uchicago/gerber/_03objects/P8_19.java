package edu.uchicago.gerber._03objects;

import java.util.Scanner;

public class P8_19 {
    public static void main(String[] args) {
        double angle, velocity;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Angle[deg]:");
        angle = Math.toRadians(scanner.nextDouble());
        System.out.println("Velocity[m/s]:");
        velocity = scanner.nextDouble();
        Cannonball cannonball = new Cannonball(0);
        cannonball.shoot(angle,velocity);
    }
}

class Cannonball {
    private double xpos, ypos=0, xvel, yvel;

    public Cannonball(double xpos) {
        this.xpos = xpos;
    }

    public void move(double sec) {
        xpos += xvel*sec;
        ypos += yvel*sec;
        yvel -= 9.81*sec;
    }

    public double getX() {
        return xpos;
    }

    public double getY() {
        return ypos;
    }

    public void shoot(double angle, double velocity) {
        xvel = velocity*Math.cos(angle);
        yvel = velocity*Math.sin(angle);
        while (ypos >= 0){
            System.out.printf("X:%.2f Y:%.2f\n",getX(),getY());
            move(0.1);
        }
    }
}
