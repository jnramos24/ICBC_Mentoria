package com.example.resoluciones_mentoria.entities;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(double initialBalance) {

        super(initialBalance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("El monto debe ser positivo.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Fondos insuficientes para realizar la extracción.");
            return false;
        }
        balance -= amount;
        return true;
    }
}

