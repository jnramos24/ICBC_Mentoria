package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.BalanceOperations;
import com.example.resoluciones_mentoria.interfaces.DepositOperations;
import com.example.resoluciones_mentoria.interfaces.LoanOperations;
import com.example.resoluciones_mentoria.interfaces.NotificationOperations;

public class PremiumAccount implements BalanceOperations, DepositOperations, NotificationOperations, LoanOperations {

    private double saldoInicial;

    public PremiumAccount(double saldoInicial) {
        this.saldoInicial = saldoInicial;
    }

    @Override

    public void checkBalance() {
        System.out.println("Su saldo es: " + saldoInicial);
    }

    @Override
    public void deposit(double amount) {

        double totalSaldo = saldoInicial + amount;

        System.out.println("Se realizó un depósito de $ " + amount + " . Su saldo actual es de $ " + totalSaldo);

    }


    @Override

    public void receiveSpecialNotification() {
        System.out.println("Se generó el reporte financiero de su cuenta corporativa.");
    }

    @Override
    public void requestLoan() {
        System.out.println("Préstamo solicitado para la cuenta.");
    }


}