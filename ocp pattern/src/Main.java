import entities.EmailNotification;
import entities.NotificationSender;
import entities.SMSNotification;
import entities.whatsappNotification;
import interfaces.Notification;

public class Main {
    public static void main(String[] args) {
        NotificationSender notificationSender = new NotificationSender();

        Notification smsNotification = new SMSNotification();
        Notification emailNotification = new EmailNotification();
        Notification whatsappNotification = new whatsappNotification();

        notificationSender.sendNotification(smsNotification);
        System.out.println();

        notificationSender.sendNotification(emailNotification);
        System.out.println();

        notificationSender.sendNotification(whatsappNotification);
        System.out.println();

    }
}