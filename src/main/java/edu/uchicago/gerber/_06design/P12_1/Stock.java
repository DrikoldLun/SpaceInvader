package edu.uchicago.gerber._06design.P12_1;

import java.util.ArrayList;
import java.util.Scanner;

public class Stock {
    private ArrayList<Product> products;
    private Scanner scanner;
    private CashRegister cashRegister;

    public Stock(ArrayList<Product> products) {
        this.products = products;
        scanner = new Scanner(System.in);
        cashRegister = new CashRegister();
    }

    public void buy() {
        System.out.printf("Select the product you want to buy(1-%d):", products.size());
        int product_id = scanner.nextInt();
        while (product_id < 1 || product_id > products.size()) {
            System.out.printf("Input the correct product id(1-%d):", products.size());
            product_id = scanner.nextInt();
        }
        Product product = products.get(product_id - 1);
        System.out.println("You select product "+product_id+":"+product.getName()+" price:"+product.getPrice());
        product.buy(cashRegister.collect());
    }

    public void showproduct() {
        int i = 1;
        System.out.println("Product list:");
        for (Product product : products) {
            System.out.printf("id:%d, product:%s, price:%.2f, quantity:%d\n",i,product.getName(),product.getPrice(),product.getQuantity());
            i += 1;
        }
    }
}
