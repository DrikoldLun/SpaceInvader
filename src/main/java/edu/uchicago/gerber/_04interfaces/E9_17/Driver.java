package edu.uchicago.gerber._04interfaces.E9_17;

import java.util.ArrayList;

public class Driver {
    public static void main(String[] args) {
        ArrayList<SodaCan> arr = new ArrayList();
        for (int i=1;i<9;i++) {
            arr.add(new SodaCan(i,20-i));
        }
        double surface_area = 0;
        for (SodaCan sodaCan : arr) {
            surface_area += sodaCan.getSurfaceArea();
        }
        double avg_surface_area = surface_area / arr.size();
        System.out.println(avg_surface_area);
    }
}
