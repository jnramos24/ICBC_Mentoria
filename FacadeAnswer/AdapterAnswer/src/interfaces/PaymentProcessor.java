package interfaces;

import exceptions.PaymentFailedException;

public interface PaymentProcessor {

    void processPayment(double amount, String paymentId) throws PaymentFailedException;
}
