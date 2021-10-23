package edu.uchicago.gerber._04interfaces.E9_8;

class BasicAccount extends BankAccount {
    @Override
    public void withdraw(double amount) {
        if (amount <= balance){
            balance -= amount;
        }
    }
}
