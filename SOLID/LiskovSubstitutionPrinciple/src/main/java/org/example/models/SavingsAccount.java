package org.example.models;

public class SavingsAccount extends BankAccount {

    private int withdrawalCount = 0;
    private static final int MAX_WITHDRAWALS = 3;
    private static final double INTEREST_RATE = 0.03;

    public SavingsAccount(double initialBalance) {
        super(initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (withdrawalCount >= MAX_WITHDRAWALS) {
            System.out.println("Monthly withdrawal limite exceeded!");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient funds!");
            return;
        }

        balance -= amount;
        withdrawalCount++;
        System.out.println("You withdrew $" + amount + " from your account!");
    }

    public void addInterest() {
        balance += balance * INTEREST_RATE;
    }
}