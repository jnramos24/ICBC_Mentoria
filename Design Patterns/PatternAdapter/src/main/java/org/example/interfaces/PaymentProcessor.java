package org.example.interfaces;

import org.example.exceptions.PaymentFailedException;

public interface PaymentProcessor {
    void processPayment(double amount, String idPayment) throws PaymentFailedException;
}