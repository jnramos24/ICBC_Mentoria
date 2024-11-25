import entities.EmailNotification;
import entities.NotificationSender;
import entities.SMSNotification;
import entities.WzpNotification;
import interfaces.Notification;

public class Main {
    public static void main(String[] args) {

        NotificationSender notificationSender = new NotificationSender();

        Notification notificationSms = new SMSNotification();
        Notification notificationEmail = new EmailNotification();
        Notification notificationWzp = new WzpNotification();

        notificationSender.sendNotification(notificationSms);
        System.out.println();
        notificationSender.sendNotification(notificationEmail);
        System.out.println();
        notificationSender.sendNotification(notificationWzp);
    }
}
