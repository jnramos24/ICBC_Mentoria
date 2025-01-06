package messages;

import channels.NotificationChannel;

public class CriticalMessage extends Message {

    public CriticalMessage(NotificationChannel notificationChannel) {
        super(notificationChannel);
    }

    @Override
    public void sendMessage(String msg) {
        notificationChannel.send("");
        System.out.println("ALERTA CRITICA: " + msg);
    }
}
