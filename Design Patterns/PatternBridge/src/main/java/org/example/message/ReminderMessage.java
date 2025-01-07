package org.example.message;

import org.example.channel.interfaces.NotificationChannel;

public class ReminderMessage extends Message {
    public ReminderMessage(NotificationChannel channel) {
        super(channel);
    }

    @Override
    public void sendMessage(String msg) {
        String finalMsg = "Reminder: " + msg;
        channel.send(finalMsg);
    }
}