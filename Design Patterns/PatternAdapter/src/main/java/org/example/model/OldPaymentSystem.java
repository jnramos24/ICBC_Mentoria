package org.example.model;

import org.example.interfaces.PaymentProcessor;

public class OldPaymentSystem implements PaymentProcessor {
    @Override
    public void processPayment(double amount, String idPayment) {
        System.out.println("Processing payment with id: " + idPayment + " and amount: $" + amount + " through OldPaymentSystem.");
    }
}
