package messages;

import channels.NotificationChannel;

public class ReminderMessage extends Message {
    public ReminderMessage(NotificationChannel notificationChannel) {
        super(notificationChannel);
    }

    @Override
    public void sendMessage(String msg) {
        String content = "Recordatorio: ";
        notificationChannel.send(content + msg);
    }
}
