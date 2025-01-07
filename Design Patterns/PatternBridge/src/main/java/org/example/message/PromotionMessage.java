package org.example.message;

import org.example.channel.interfaces.NotificationChannel;

public class PromotionMessage extends Message {

    public PromotionMessage(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void sendMessage(String msg) {
        String finalMsg = "Promotion: " + msg;
        channel.send(finalMsg);
    }
}