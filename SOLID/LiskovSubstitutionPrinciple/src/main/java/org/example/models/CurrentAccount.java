package org.example.models;

public class CurrentAccount extends BankAccount {
    public CurrentAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return;
        }

        balance -= amount;
        System.out.println("You withdrew $" + amount + " from your account!");
    }
}