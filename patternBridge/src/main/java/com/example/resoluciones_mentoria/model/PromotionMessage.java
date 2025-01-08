package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.NotificationChannel;

public class PromotionMessage extends Message{

    public PromotionMessage (NotificationChannel channel){
        super(channel);
    }
    @Override
    public void sendMessage(String msg) {
        String content = " PROMOCIÓN " + msg;
        channel.send(content);
    }
}
