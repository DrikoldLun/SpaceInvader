package edu.uchicago.gerber._06design.P12_1;

public class Product {
    private String name;
    private double price;
    private int quantity;

    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void buy(double money) {
        if (quantity == 0) {
            System.out.println("Transaction failed and money was returned! The product "+name+" is sold out!");
        } else {
            if (money >= price) {
                quantity -= 1;
                System.out.println("Transaction succeeded! Product:" + name);
            } else {
                System.out.println("Transaction failed and money was returned! Insufficient money was supplied!");
            }
        }
    }
}
