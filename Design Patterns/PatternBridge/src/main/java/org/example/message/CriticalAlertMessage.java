package org.example.message;

import org.example.channel.interfaces.NotificationChannel;

public class CriticalAlertMessage extends Message {
    public CriticalAlertMessage(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void sendMessage(String msg) {
        String finalMsg= "CRITICAL ALERT: " + msg;
        channel.send(finalMsg);
    }
}