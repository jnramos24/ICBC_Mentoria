package org.example.entities;

import org.example.interfaces.BalanceOperations;
import org.example.interfaces.DepositOperations;
import org.example.interfaces.ReportOperations;
import org.example.interfaces.TransferOperations;

public class CorporateAccount implements BalanceOperations, DepositOperations, ReportOperations, TransferOperations {

    private double balance;

    public CorporateAccount(double initialBalance) {
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
    public void generateFinancialReport() {
        System.out.println("Financial report generated for your corporate account!");
    }

    @Override
    public void internationalTransfer(double amount) {
        if (amount <= balance){
            balance -= amount;
            System.out.println("The international transfer for the amount of $" + amount + " was completed!");
        }
    }
}
