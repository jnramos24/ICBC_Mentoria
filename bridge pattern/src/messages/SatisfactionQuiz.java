package messages;

import channels.NotificationChannel;

public class SatisfactionQuiz extends Message {

    public SatisfactionQuiz(NotificationChannel notificationChannel) {
        super(notificationChannel);
    }

    @Override
    public void sendMessage(String msg) {
        String content = "Encuesta de satisfaccion: ";
        notificationChannel.send(content + msg);

    }
}
