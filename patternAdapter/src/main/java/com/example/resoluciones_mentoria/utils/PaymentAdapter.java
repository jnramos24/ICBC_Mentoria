package com.example.resoluciones_mentoria.utils;

import com.example.resoluciones_mentoria.exceptions.PaymentFailedException;
import com.example.resoluciones_mentoria.interfaces.PaymentProcessor;
import com.example.resoluciones_mentoria.model.NewPaymentProcessor;

public class PaymentAdapter implements PaymentProcessor {

    private NewPaymentProcessor newPaymentProcessor;

    public PaymentAdapter(NewPaymentProcessor newPaymentProcessor) {
        this.newPaymentProcessor = newPaymentProcessor;
    }

    @Override
    public void processPayment(double amount) throws PaymentFailedException {
        newPaymentProcessor.makePayment(amount);
    }
}

