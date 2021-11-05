package edu.uchicago.gerber._06design.P12_1;

import java.util.ArrayList;

public class VendingMachine {
    private Stock stock;

    public VendingMachine() {
        initializestock();
        work();
    }

    public void work() {
        while(true) {
            stock.showproduct();
            stock.buy();
        }
    }

    public void initializestock() {
        ArrayList<Product> products = new ArrayList<Product>() {
            {
                add(new Product("Soda",2.5,5));
                add(new Product("Coke",2,10));
                add(new Product("Chips",3,7));
                add(new Product("Ramen",1,3));
                add(new Product("Bread",5,3));
                add(new Product("Tissue",0.5,20));
            }
        };
        stock = new Stock(products);
    }

}
