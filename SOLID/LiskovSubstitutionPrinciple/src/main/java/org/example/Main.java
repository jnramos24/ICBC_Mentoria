package org.example;

import org.example.models.BankAccount;
import org.example.models.CurrentAccount;
import org.example.models.SavingsAccount;

public class Main {
    public static void main(String[] args) {
        SavingsAccount savings = new SavingsAccount(1000);
        BankAccount current = new CurrentAccount(2000);

        System.out.println("\n----- SAVINGS ACCOUNT -----");
        System.out.println("Initial balance savings account: " + savings.getBalance());

        savings.withdraw(200);
        savings.withdraw(200);
        savings.withdraw(200);
        savings.withdraw(200);

        System.out.println("\n----- CURRENT ACCOUNT -----");
        System.out.println("Initial balance current account: "+ current.getBalance());
        current.withdraw(500);
        current.withdraw(2000);

        if(savings instanceof SavingsAccount){
            savings.addInterest();
            System.out.println("Current balance after interest: " + savings.getBalance());
        }
    }
}