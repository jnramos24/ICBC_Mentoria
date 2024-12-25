package org.example.subsystems;

public class Shipping {
    public void arrangeShipping(Long productId, Long customerId) {
        System.out.println("Arranging shipping for product ID: " + productId + " to customer ID: " + customerId);
    }
}
