package messages;

import channels.NotificationChannel;

//Clase abstracta para mensajes
public abstract class Message {

    //Inyecto la interfaz
    protected NotificationChannel notificationChannel;

    //Constructor que conecta el mensaje con el canal
    public Message(NotificationChannel notificationChannel) {
        this.notificationChannel = notificationChannel;
    }

    //Metodo abstracto para ser llamado por los tipos de mensaje
    public abstract void sendMessage(String msg);
}
