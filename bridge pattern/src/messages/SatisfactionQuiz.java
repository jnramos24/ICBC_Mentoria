package messages;

import channels.NotificationChannel;

public class SatisfactionQuiz extends Message {

    public SatisfactionQuiz(NotificationChannel notificationChannel) {
        super(notificationChannel);
    }

    @Override
    public void sendMessage(String msg) {
        notificationChannel.send("");
        System.out.println("Encuesta de satisfaccion: " + msg);

    }
}
