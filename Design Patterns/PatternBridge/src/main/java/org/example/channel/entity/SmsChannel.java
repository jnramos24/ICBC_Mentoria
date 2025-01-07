package org.example.channel.entity;

import org.example.channel.interfaces.NotificationChannel;

public class SmsChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("SENDING SMS | " + content);
    }
}