package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public class CriticalAlertMessage extends Message{

    public CriticalAlertMessage (NotificationChannel channel){
        super(channel);
    }
    @Override
    public void sendMessage(String msg) {
        String content = " CRITICAL ALERT " + msg;
        channel.send(content);
    }
}
