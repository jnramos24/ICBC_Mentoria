package com.example.resoluciones_mentoria.implementation;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public class EmailChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("Enviando correo electrónico: " + content);
    }
}
