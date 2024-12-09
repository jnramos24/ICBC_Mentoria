package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.BalanceOperations;
import com.example.resoluciones_mentoria.interfaces.DepositOperations;
import com.example.resoluciones_mentoria.interfaces.LoanOperations;

public class BasicAccount implements BalanceOperations,  DepositOperations{

    private double saldoInicial;

    public BasicAccount (double saldoInicial){
        this.saldoInicial = saldoInicial;
    }

    @Override

    public void checkBalance() {
        System.out.println("Su saldo es: " + saldoInicial);
    }

    @Override
    public void deposit(double amount){

       double totalSaldo = saldoInicial + amount;

        System.out.println("Se realizó un depósito de $ " + amount + " . Su saldo actual es de $ " + totalSaldo);

    }


}
