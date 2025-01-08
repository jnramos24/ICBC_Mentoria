package com.example.resoluciones_mentoria.implementation;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public class PushChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("Enviando una notificación de push: " + content);
    }
}
