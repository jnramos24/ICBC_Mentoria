package org.example.entities;

import org.example.interfaces.BalanceOperations;
import org.example.interfaces.DepositOperations;
import org.example.interfaces.LoanOperations;
import org.example.interfaces.NotificationOperations;

public class PremiumAccount implements BalanceOperations, DepositOperations, LoanOperations, NotificationOperations {
    private double balance;

    public PremiumAccount(double initialBalance) {
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

    @Override
    public void requestLoan() {
        System.out.println("Your loan request was made!");
    }

    @Override
    public void receiveSpecialNotification() {
        System.out.println("You received a special notification in your premiun account!");
    }
}