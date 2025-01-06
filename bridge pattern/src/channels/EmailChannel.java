package channels;

public class EmailChannel implements NotificationChannel {

    @Override
    public void send(String content) {
        System.out.println("Enviando correo: " + content);
    }
}
