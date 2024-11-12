package utils;

import exceptions.PaymentFailedException;
import interfaces.PaymentProcessor;
import models.NewPaymentProcessor;

public class PaymentAdapter implements PaymentProcessor {
    private NewPaymentProcessor newPaymentProcessor;

    public PaymentAdapter(){
        this.newPaymentProcessor = new NewPaymentProcessor();
    }
    @Override
    public void processPayment(double amount, String paymentId) throws PaymentFailedException {
        newPaymentProcessor.makePayment(amount,paymentId);
    }
}
