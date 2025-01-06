package channels;

public class SlackChannel implements NotificationChannel {
    @Override
    public void send(String content) {
        System.out.println("Enviando por Slack:" + content);
    }
}
