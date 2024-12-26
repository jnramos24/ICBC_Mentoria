package com.example.resoluciones_mentoria;

import com.example.resoluciones_mentoria.entities.BankAccount;
import com.example.resoluciones_mentoria.entities.CurrentAccount;
import com.example.resoluciones_mentoria.entities.SavingAccount;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolucionesMentoriaApplication.class, args);


	List<BankAccount> accounts = new ArrayList<>();

	SavingAccount savingAccount = new SavingAccount(3000);
	CurrentAccount currentAccount = new CurrentAccount(3000);

	accounts.add(savingAccount);
	accounts.add(currentAccount);

	 System.out.println("Operaciones Bancarias");
        for (BankAccount account : accounts) {
		System.out.println("El saldo inicial de tu cuenta es: " + account.getBalance());
		account.withdraw(800);
		System.out.println("El saldo de tu cuenta después del retiro es: " + account.getBalance());
	}

		System.out.println("Funcionalidad específica de la cuenta bancaria");
		savingAccount.addInterest();
		System.out.println("Saldo después de los intereses: " + savingAccount.getBalance());

		savingAccount.withdraw(600);
		savingAccount.withdraw(800);
		savingAccount.withdraw(10);
		savingAccount.withdraw(900);

		System.out.println("Transferencia Bancaria:");
		boolean transferSuccess = currentAccount.transfer(savingAccount, 1000);
		System.out.println("Transferencia exitosa: " + transferSuccess);
		System.out.println("Saldo Cuenta Corriente: " + currentAccount.getBalance());
		System.out.println("Saldo Cuenta Ahorro: " + savingAccount.getBalance());

	}
}
