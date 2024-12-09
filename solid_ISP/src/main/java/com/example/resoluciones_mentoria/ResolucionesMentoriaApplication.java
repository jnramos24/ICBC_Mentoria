package com.example.resoluciones_mentoria;

import com.example.resoluciones_mentoria.model.BasicAccount;
import com.example.resoluciones_mentoria.model.CorporateAccount;
import com.example.resoluciones_mentoria.model.PremiumAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolucionesMentoriaApplication.class, args);

		// Cuenta básica
		BasicAccount basicAccount = new BasicAccount(1000);
		basicAccount.checkBalance();
		basicAccount.deposit(200);

		System.out.println();

		// Cuenta premium
		PremiumAccount premiumAccount = new PremiumAccount(5000);
		premiumAccount.checkBalance();
		premiumAccount.deposit(500);
		premiumAccount.requestLoan();
		premiumAccount.receiveSpecialNotification();

		System.out.println();

		// Cuenta corporativa
		CorporateAccount corporateAccount = new CorporateAccount(20000);
		corporateAccount.checkBalance();
		corporateAccount.deposit(5000);
		corporateAccount.generateFinancialReport();
		corporateAccount.internationalTransfer(10000);
	}
}



