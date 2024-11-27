package entities;

import interfaces.BalanceOperations;
import interfaces.DepositOperations;
import interfaces.LoanOperations;
import interfaces.NotificationOperations;

public class PremiumAccount implements BalanceOperations, DepositOperations, LoanOperations, NotificationOperations {
    private double balance;

    public PremiumAccount(double initialBalance) {
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

    @Override
    public void requestLoan() {
        System.out.println("Su pedido de prestamo fue realizado.");
    }

    @Override
    public void receiveSpecialNotification() {
        System.out.println("Recibiste una notificacion especial en tu cuenta premium.");
    }
}
