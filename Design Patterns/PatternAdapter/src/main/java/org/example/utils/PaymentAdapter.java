package org.example.utils;

import org.example.model.NewPaymentProcessor;
import org.example.exceptions.PaymentFailedException;
import org.example.interfaces.PaymentProcessor;

public class PaymentAdapter implements PaymentProcessor {

    private NewPaymentProcessor newPaymentProcessor;

    public PaymentAdapter() {
        this.newPaymentProcessor = new NewPaymentProcessor();
    }

    @Override
    public void processPayment(double amount, String idPayment) throws PaymentFailedException {
        newPaymentProcessor.makePayment(amount, idPayment);
    }
}
