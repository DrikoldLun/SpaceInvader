package edu.uchicago.gerber._04interfaces.E9_8;

public class Driver {
    public static void main(String[] args) {
        BasicAccount basicAccount = new BasicAccount();
        basicAccount.deposit(1000);
        System.out.println(basicAccount.getBalance());
        basicAccount.withdraw(2000);
        System.out.println(basicAccount.getBalance());
        basicAccount.withdraw(500);
        System.out.println(basicAccount.getBalance());
    }
}
