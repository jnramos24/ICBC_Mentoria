package org.example.subsystems;

public class Notification {
    public void sendOrderConfirmation(Long customerId) {
        System.out.println("Sending order confirmation to customer ID: " + customerId);
    }
}