package messages;

import channels.NotificationChannel;

public class CriticalMessage extends Message {

    //Constructor que llama al constructor de la clase abstracta
    public CriticalMessage(NotificationChannel notificationChannel) {
        super(notificationChannel);
    }

    @Override
    public void sendMessage(String msg) {
        String content = "ALERTA CRITICA: ";
        notificationChannel.send(content + msg);
    }
}
