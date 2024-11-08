package utils;

import subsystems.Inventory;
import subsystems.Notification;
import subsystems.Payment;
import subsystems.Shipping;

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

    public void placeOrder(String productId, String customerId, double amount){
        if(inventory.checkProductAvailability(productId)){
            payment.processPayment(customerId,amount);
            shipping.processingShipping(productId,customerId);
            notification.sendOrderConfirmation(customerId);
            System.out.println("La orden fue realizada");
        } else{
            System.out.println("el producto no esta disponible");
        }
    }

}
