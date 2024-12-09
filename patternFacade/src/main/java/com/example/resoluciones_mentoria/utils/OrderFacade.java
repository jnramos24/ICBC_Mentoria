package com.example.resoluciones_mentoria.utils;

import com.example.resoluciones_mentoria.subsistem.Inventory;
import com.example.resoluciones_mentoria.subsistem.Notification;
import com.example.resoluciones_mentoria.subsistem.Payment;
import com.example.resoluciones_mentoria.subsistem.Shipping;

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

    public void placeOrder(String productId, String clientId, double amount) {
        if (inventory.checkAvailability(productId)) {
            payment.processPayment(clientId, amount);
            shipping.sendShippingConfirmation(productId, clientId);
            notification.sendConfirmation(clientId);
            System.out.println("La orden fue realizada con éxito");
        } else {
            System.out.println("El producto no se encuentra disponible");
        }
    }

}

