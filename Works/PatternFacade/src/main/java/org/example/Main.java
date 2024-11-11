package org.example;

import org.example.utils.OrderFacade;

public class Main {
    public static void main(String[] args) {
        OrderFacade orderFacade = new OrderFacade();

        Long productId = 33L;
        Long customerId = 3L;
        double amount = 3000.00;

        orderFacade.placeOrder(productId, customerId, amount);
    }
}