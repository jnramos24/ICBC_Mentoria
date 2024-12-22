package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.utils.Notification;

public class EmailNotification implements Notification {

    @Override
    public void send() {
        System.out.println("Estamos enviando la notificación por email");
    }

}
