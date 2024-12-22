package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.utils.Notification;

public class NotificationSender {

    public void sendNotification(Notification notification){
        notification.send();
    }
}
