package org.example;

import org.example.utils.PaymentAdapter;
import org.example.interfaces.PaymentProcessor;
import org.example.model.NewPaymentProcessor;
import org.example.exceptions.PaymentFailedException;
import org.example.model.OldPaymentSystem;

public class Main {
    public static void main(String[] args) {
        PaymentProcessor oldPaymentSystem = new OldPaymentSystem();
        PaymentProcessor adapter = new PaymentAdapter();

        try {
            System.out.println("Using OldPaymentSystem:");
            oldPaymentSystem.processPayment(500.0, "A1");

            System.out.println("\nUsing NewPaymentProcessor through PaymentAdapter:");
            adapter.processPayment(800.0, "C3");

            System.out.println("\nAttempting to process invalid payment through NewPaymentProcessor:");
            adapter.processPayment(1200.0, "B2");
        } catch (PaymentFailedException e) {
            System.err.println("Payment failed: " + e.getMessage());
        }
    }
}