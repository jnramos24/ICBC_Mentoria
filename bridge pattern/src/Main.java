import channels.EmailChannel;
import channels.NotificationChannel;
import channels.PushChannel;
import channels.SlackChannel;
import channels.SmsChannel;
import messages.CriticalMessage;
import messages.Message;
import messages.PromotionMessage;
import messages.ReminderMessage;
import messages.SatisfactionQuiz;

public class Main {
    public static void main(String[] args) {
        //Crear un canal de correo electronico
        NotificationChannel emailChannel = new EmailChannel();
        Message criticalAlert = new CriticalMessage(emailChannel);
        criticalAlert.sendMessage("Problemas en el sistema!");

        //Crear un mensaje de promocion y enviarlo por SMS
        NotificationChannel smsChannel = new SmsChannel();
        Message promotion = new PromotionMessage(smsChannel);
        promotion.sendMessage("20% de descuento en tu proxima compra");

        //Crear un canal Push
        NotificationChannel pushChannel = new PushChannel();
        Message reminder = new ReminderMessage(pushChannel);
        reminder.sendMessage("Recuerda pagar tus facturas");

        //Crear un canal Slack
        NotificationChannel slackChannel = new SlackChannel();
        Message quiz = new SatisfactionQuiz(slackChannel);
        quiz.sendMessage("Por favor completa la encuesta");
    }
}