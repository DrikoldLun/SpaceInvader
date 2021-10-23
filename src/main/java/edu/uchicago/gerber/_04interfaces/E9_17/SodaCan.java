package edu.uchicago.gerber._04interfaces.E9_17;

class SodaCan implements Measurable{
    private double height;
    private double radius;

    public SodaCan(double height, double radius) {
        this.height = height;
        this.radius = radius;
    }

    @Override
    public double getSurfaceArea(){
        return 2*Math.PI*radius*(height+radius);
    }
}