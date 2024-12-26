package org.example;

import org.example.entities.EmailNotification;
import org.example.entities.NotificationSender;
import org.example.entities.SMSNotification;
import org.example.interfaces.Notification;

public class Main {
    public static void main(String[] args) {
        NotificationSender notificationSender = new NotificationSender();

        Notification notificationSms = new SMSNotification();
        Notification notificationEmail = new EmailNotification();

        notificationSender.sendNotification(notificationSms);
        notificationSender.sendNotification(notificationEmail);
    }
}