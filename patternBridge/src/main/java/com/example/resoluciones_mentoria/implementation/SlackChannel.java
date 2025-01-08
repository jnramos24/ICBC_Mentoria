package com.example.resoluciones_mentoria.implementation;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public class SlackChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("Enviando un mensaje en Slack: " + content);
    }
}
