package org.example.entities;

import org.example.interfaces.Notification;

public class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Sending email notification!");
    }
}