package org.example.model;

import org.example.exceptions.PaymentFailedException;

public class NewPaymentProcessor {
    public void makePayment(double amount, String idPayment) throws PaymentFailedException {
        if (amount > 1000) {
            throw new PaymentFailedException("Payment failed with id: "+ idPayment + " the amount exceeds the limit");
        }
        System.out.println("Processing payment of $" + amount + " through NewPaymentProcessor.");
    }
}
