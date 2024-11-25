package entities;

import interfaces.Notification;

public class WzpNotification implements Notification {
    @Override
    public void send() {
        System.out.println("Enviando notificacion por wazaaaa");
    }
}
