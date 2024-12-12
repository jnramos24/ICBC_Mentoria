package interfaces;

import exceptions.PaymentFailException;

public interface PaymentProcessor {
    void processPayment(double var1, String var3) throws PaymentFailException;
}

