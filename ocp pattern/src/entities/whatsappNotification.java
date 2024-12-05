package entities;

import interfaces.Notification;

public class whatsappNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Enviando notificacion por Whatsapp...");
    }
}
