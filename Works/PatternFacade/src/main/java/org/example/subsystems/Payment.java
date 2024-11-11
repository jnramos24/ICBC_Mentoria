package org.example.subsystems;

public class Payment {
    public void processPayment(Long customerId, double amount) {
        System.out.println("Processing payment for customer ID: " + customerId + " with amount: $" + amount);
    }
}
