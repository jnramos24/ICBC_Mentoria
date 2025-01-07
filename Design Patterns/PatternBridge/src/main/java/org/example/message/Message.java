package org.example.message;

import org.example.channel.interfaces.NotificationChannel;

public abstract class Message {
    protected NotificationChannel channel;

    public Message(NotificationChannel channel) {
        this.channel = channel;
    }

    public abstract void sendMessage(String msg);
}