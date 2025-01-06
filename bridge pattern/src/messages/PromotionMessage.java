package messages;

import channels.NotificationChannel;

public class PromotionMessage extends Message {
    public PromotionMessage(NotificationChannel notificationChannel) {
        super(notificationChannel);
    }

    @Override
    public void sendMessage(String msg) {
        notificationChannel.send("");
        System.out.println("Promocion: " + msg);
    }
}
