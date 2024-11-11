package org.example.subsystems;

public class Inventory {
    public boolean checkProductAvailability(Long productId) {
        System.out.println("Checking availability for product ID: " + productId);
        return true;
    }
}