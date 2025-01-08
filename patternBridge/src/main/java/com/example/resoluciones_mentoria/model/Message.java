package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public abstract class Message{
    protected NotificationChannel channel;

    public Message(NotificationChannel channel) {
        this.channel = channel;
    }

    public abstract void sendMessage(String msg);
}

