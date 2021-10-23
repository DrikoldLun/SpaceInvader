package edu.uchicago.gerber._04interfaces.E9_13;

import java.awt.*;

class BetterRectangle extends Rectangle {
    public double getPerimeter() {
        return (height+width)*2;
    }

    public double getArea() {
        return height*width;
    }
}
