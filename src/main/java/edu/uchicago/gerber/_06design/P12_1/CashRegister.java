package edu.uchicago.gerber._06design.P12_1;

import java.util.ArrayList;
import java.util.Scanner;

public class CashRegister {

    private Scanner scanner;

    public CashRegister() {
        scanner = new Scanner(System.in);
    }

    public double collect() {
        System.out.println("Please start inserting coins (supported type A-E)");
        ArrayList<Coin> coins = new ArrayList<Coin>();
        while(true) {
            System.out.print("Insert coin (Enter \"stop\" to stop inserting coin):");
            String cointype = scanner.nextLine();
            if (cointype.equals("stop")) {
                break;
            } else {
                coins.add(new Coin(cointype));
            }
            System.out.println("Current money inserted: "+coins2money(coins));
        }
        return coins2money(coins);
    }

    public double coins2money(ArrayList<Coin> coins) {
        double money = 0;
        for (Coin coin : coins) {
            money += coin.getValue();
        }
        return money;
    }
}
