package com.example.resoluciones_mentoria.entities;

public abstract class BankAccount {

    protected double balance;

    public BankAccount (double initialBalance){

        if(initialBalance <0){
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo");
        }

        this.balance= initialBalance;
    }

    public double getBalance(){

        return balance;
    }

    public abstract boolean withdraw(double amount);

    public boolean transfer(BankAccount target, double amount) {
        if (amount <= 0) {
            System.out.println("La cantidad a transferir debe ser positiva.");
            return false;
        }
        
        if (withdraw(amount)) {
            target.deposit(amount);
            return true;
        }
        return false;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("La cantidad a depositar debe ser positiva.");
        }
        balance += amount;

    }
}