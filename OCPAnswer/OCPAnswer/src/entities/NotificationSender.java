package entities;

import interfaces.Notification;

public class NotificationSender{

    public void sendNotification(Notification notification){
        notification.send();
    }
}
