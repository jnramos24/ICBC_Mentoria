package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public class ReminderMessage extends Message{

    public ReminderMessage (NotificationChannel channel){
        super(channel);
    }
    @Override
    public void sendMessage(String msg) {
        String content = " RECORDATORIO " + msg;
        channel.send(content);
    }
}