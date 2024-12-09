package com.example.resoluciones_mentoria;

import com.example.resoluciones_mentoria.exceptions.PaymentFailedException;
import com.example.resoluciones_mentoria.interfaces.PaymentProcessor;
import com.example.resoluciones_mentoria.model.NewPaymentProcessor;
import com.example.resoluciones_mentoria.model.OldPaymentSistem;
import com.example.resoluciones_mentoria.utils.PaymentAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolucionesMentoriaApplication.class, args);

		try {

			PaymentProcessor oldPaymentProcessor = new OldPaymentSistem();
			oldPaymentProcessor.processPayment(100.0);


			NewPaymentProcessor newProcessor = new NewPaymentProcessor();
			PaymentProcessor adapter = new PaymentAdapter(newProcessor);

			adapter.processPayment(200.0);
			adapter.processPayment(-50.0);

		} catch (PaymentFailedException e) {

			System.err.println("Hubo un error al procesar el pago: " + e.getMessage());

		} catch (Exception e) {

			System.err.println("Hubo un error inesperado: " + e.getMessage());
		}
	}
}