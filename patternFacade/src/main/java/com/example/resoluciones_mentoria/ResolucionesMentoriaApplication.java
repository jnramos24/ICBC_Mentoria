package com.example.resoluciones_mentoria;

import com.example.resoluciones_mentoria.utils.OrderFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolucionesMentoriaApplication.class, args);

		OrderFacade orderFacade = new OrderFacade();

		String productId = "P98765";
		String clientId = "C123";
		double amount = 800.00;

		orderFacade.placeOrder(productId,clientId,amount);
	}
}




