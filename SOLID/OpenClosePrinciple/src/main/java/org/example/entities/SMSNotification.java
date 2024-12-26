package org.example.entities;

import org.example.interfaces.Notification;

public class SMSNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Sending SMS notification!");
    }
}
