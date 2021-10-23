package edu.uchicago.gerber._04interfaces.E9_8;

class BankAccount {

    protected double balance=0;

    public void deposit(double amount){
        balance += amount;
    }

    public void withdraw(double amount){
        balance -= amount;
    }

    public double getBalance(){
        return balance;
    }

    public void monthEnd(){}
}