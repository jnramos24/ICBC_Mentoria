package org.example.channel.entity;

import org.example.channel.interfaces.NotificationChannel;

public class EmailChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("SENDING EMAIL | " + content);
    }
}