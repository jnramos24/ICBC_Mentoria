package entities;

import interfaces.Notification;

public class SMSNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Enviando notificación por sms...");
    }
}
