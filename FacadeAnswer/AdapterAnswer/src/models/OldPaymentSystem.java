package models;

import interfaces.PaymentProcessor;

public class OldPaymentSystem implements PaymentProcessor {
    @Override
    public void processPayment(double amount, String paymentId) {
        System.out.println("Pago con id: " +paymentId+ " procesado por el monto de: $" + amount);
    }
}
