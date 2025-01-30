import implementations.EmailChannel;
import implementations.PushChannel;
import implementations.SmsChannel;
import interfaces.NotificationChannel;
import models.CriticalAlertMessage;
import models.Message;
import models.PromotionMessage;
import models.ReminderMessage;

public class Main {
    public static void main(String[] args) {

        // Ejemplo 1: Alerta critica enviada por mail
        NotificationChannel emailChannel = new EmailChannel();
        Message criticalAlert = new CriticalAlertMessage(emailChannel);
        criticalAlert.sendMessage("¡Problema en el sistema!");

        //Ejemplo 2: Recordatorio enviado por sms
        NotificationChannel smsChannel = new SmsChannel();
        Message reminder = new ReminderMessage(smsChannel);
        reminder.sendMessage("Reunion a las 3 PM");

        //Ejemplo 3: Promoción enviada por notificación de push
        NotificationChannel pushChannel = new PushChannel();
        Message promotion = new PromotionMessage(pushChannel);
        promotion.sendMessage("Obten un 50% de descuento en tu siguiente compra");

    }
}