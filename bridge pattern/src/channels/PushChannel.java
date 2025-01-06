package channels;

public class PushChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("Enviando notificación push: " + content);
    }
}
