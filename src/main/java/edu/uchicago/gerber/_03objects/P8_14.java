package edu.uchicago.gerber._03objects;

import java.util.ArrayList;
import java.util.Scanner;

public class P8_14 {
    public static void function(ArrayList<Country> countries) {
        long maxpopu=0;
        double maxarea=0, maxpopu_dens=0;
        String maxarea_country="", maxpopu_country="", maxpopu_dens_country="";
        for (Country country : countries) {
            if (country.getArea() > maxarea) {
                 maxarea_country = country.getName();
                 maxarea = country.getArea();
            }
            if (country.getPopulation() > maxpopu) {
                maxpopu_country = country.getName();
                maxpopu = country.getPopulation();
            }
            if (country.getPopulation()/country.getArea() > maxpopu_dens) {
                maxpopu_dens_country = country.getName();
                maxpopu_dens = country.getPopulation()/country.getArea();
            }
        }
        System.out.println("The country with the largest area is "+maxarea_country);
        System.out.println("The country with the largest population is "+maxpopu_country);
        System.out.println("The country with the largest population density is "+maxpopu_dens_country);
    }

    public static void main(String[] args) {
        Country country1 = new Country("Country1",100,10);
        Country country2 = new Country("Country2",1000,10);
        Country country3 = new Country("Country3",100,20);
        ArrayList<Country> countries = new ArrayList<Country>() {
            {
                add(country1);
                add(country2);
                add(country3);
            }
        };
        function(countries);
    }
}

class Country {
    private String name;
    private long population;
    private double area;

    public Country(String name, long population, double area) {
        this.name = name;
        this.population = population;
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }
}
