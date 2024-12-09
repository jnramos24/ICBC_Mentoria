package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.*;

public class CorporateAccount implements BalanceOperations, DepositOperations, ReportOperations, TransferOperations {

    private double saldoInicial;

    public CorporateAccount (double saldoInicial){
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


    @Override

    public  void generateFinancialReport(){
        System.out.println("Se generó el reporte financiero de su cuenta corporativa.");
    }

    @Override
    public void internationalTransfer(double amount){

        if(saldoInicial >= amount){
            saldoInicial -= amount;
            System.out.println("Se pudo completar la transferencia de $ " + amount);
        }else {
            System.out.println("El saldo es insuficiente para realizar la transferencia");

    }
}
}
