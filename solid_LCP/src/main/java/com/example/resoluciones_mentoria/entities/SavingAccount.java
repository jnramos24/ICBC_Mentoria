package com.example.resoluciones_mentoria.entities;

public class SavingAccount extends BankAccount{

    private int numberOfWithdraws;

    int maxNumberOfWithdraws = 3;

    double interest = 0.10;

    public SavingAccount (double initialBalance) {

        super(initialBalance);
        this.numberOfWithdraws = 0;
    }

    public void addInterest (){

        balance += balance * interest;

    }

    @Override
    public boolean withdraw(double amount) {

        if (numberOfWithdraws > maxNumberOfWithdraws ){
            System.out.println("Se han realizado todas las extracciones permitidas durante el transcurso de este mes");
            return false;
        }

        if (amount <= 0) {
            System.out.println("El monto debe ser positivo.");
            return false;
        }

        if (amount > balance) {
                System.out.println("Los fondos de esta cuenta son insuficientes para realizar el retiro.");
                return false;
        }
        balance -= amount;

        numberOfWithdraws++;

            return true;
        }

}


