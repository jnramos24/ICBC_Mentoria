package org.example;

import org.example.entities.BasicAccount;
import org.example.entities.CorporateAccount;
import org.example.entities.PremiumAccount;

public class Main {
    public static void main(String[] args) {
        System.out.println("\n----- BASIC ACCOUNT -----");
        BasicAccount basicAccount = new BasicAccount(1000);
        basicAccount.checkBalance();
        basicAccount.deposit(200);

        System.out.println("\n----- PREMIUM ACCOUNT -----");
        PremiumAccount premiumAccount = new PremiumAccount(5000);
        premiumAccount.deposit(1000);
        premiumAccount.checkBalance();
        premiumAccount.requestLoan();
        premiumAccount.receiveSpecialNotification();

        System.out.println("\n----- CORPORATE ACCOUNT -----");
        CorporateAccount corporateAccount = new CorporateAccount(20000);
        corporateAccount.deposit(10000);
        corporateAccount.checkBalance();
        corporateAccount.generateFinancialReport();
        corporateAccount.internationalTransfer(10000);
    }
}