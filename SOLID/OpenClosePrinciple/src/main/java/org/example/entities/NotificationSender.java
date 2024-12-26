package org.example.entities;

import org.example.interfaces.Notification;

public class NotificationSender {
    public void sendNotification(Notification notification) {
        notification.send();
    }
}