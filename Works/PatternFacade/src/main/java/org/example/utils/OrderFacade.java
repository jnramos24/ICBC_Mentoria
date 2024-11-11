package org.example.utils;

import org.example.subsystems.Inventory;
import org.example.subsystems.Notification;
import org.example.subsystems.Payment;
import org.example.subsystems.Shipping;

public class OrderFacade {
    private Inventory inventory;
    private Payment payment;
    private Shipping shipping;
    private Notification notification;

    public OrderFacade() {
        this.inventory = new Inventory();
        this.payment = new Payment();
        this.shipping = new Shipping();
        this.notification = new Notification();
    }

    public void placeOrder(Long productId, Long customerId, double amount) {
        if(inventory.checkProductAvailability(productId)) {
            payment.processPayment(customerId, amount);
            shipping.arrangeShipping(productId, customerId);
            notification.sendOrderConfirmation(customerId);
            System.out.println("Order placed successfully!");
        } else {
            System.out.println("Product not available for product ID: " + productId);
        }
    }
}