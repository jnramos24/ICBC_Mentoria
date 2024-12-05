package entities;

import interfaces.Notification;

public class NotificationSender {

    public void sendNotification(Notification notification){ //inyecto un objeto del tipo Notification
        notification.send();
    }
}
