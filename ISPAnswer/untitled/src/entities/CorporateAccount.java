package entities;

import interfaces.BalanceOperations;
import interfaces.DepositOperations;
import interfaces.ReportOperations;
import interfaces.TransferOperations;

public class CorporateAccount implements BalanceOperations, DepositOperations, ReportOperations, TransferOperations {
    private double balance;

    public CorporateAccount(double initialBalance) {
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
    public void generateFinancialReport() {
        System.out.println("Reporte finaciero generado para su cuenta corporativa");
    }

    @Override
    public void internationalTransfer(double amount) {
        if (amount <= balance){
            balance -= amount;
            System.out.println("La transferencia internacional por el monto: " + amount + " Fue completada");
        }

    }
}
