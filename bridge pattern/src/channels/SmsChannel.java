package channels;

public class SmsChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("Enviando SMS: " + content);
    }
}
