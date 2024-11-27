package entities;

import interfaces.BalanceOperations;
import interfaces.DepositOperations;

public class BasicAccount implements BalanceOperations, DepositOperations {
    private double balance;

    public BasicAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    @Override
    public void checkBalance() {
        System.out.println("Su saldo es: " + balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Su deposito por: " + amount + " fue realizado.");
    }
}
