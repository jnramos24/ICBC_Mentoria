package org.example.entities;

import org.example.interfaces.BalanceOperations;
import org.example.interfaces.DepositOperations;

public class BasicAccount implements BalanceOperations, DepositOperations {
    private double balance;

    public BasicAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void checkBalance() {
        System.out.println("Your balance is: $" + balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Your $" + amount + " deposit was made!");
    }
}