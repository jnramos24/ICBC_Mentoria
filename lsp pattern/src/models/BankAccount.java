package models;

public abstract class BankAccount {

    protected double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public abstract void withdraw(double amount);
}
