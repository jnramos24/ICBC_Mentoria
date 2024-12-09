package com.example.resoluciones_mentoria.subsistem;

public class Shipping {

    public void sendShippingConfirmation(String productId, String clientId) {
        System.out.println("Se ha enviado la confirmación del inicio del recorrido del producto: " + productId + " al cliente " + clientId);
    }
}
