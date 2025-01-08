package com.example.resoluciones_mentoria.implementation;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public class SmsChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println(" Enviando un mensaje de texto: " + content);
    }
}
