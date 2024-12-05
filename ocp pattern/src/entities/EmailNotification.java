package entities;

import interfaces.Notification;

public class EmailNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Enviando notificacion por email...");
    }
}
