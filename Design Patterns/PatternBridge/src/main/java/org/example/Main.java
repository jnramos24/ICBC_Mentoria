package org.example;

import org.example.channel.entity.EmailChannel;
import org.example.channel.entity.PushChannel;
import org.example.channel.entity.SmsChannel;
import org.example.channel.interfaces.NotificationChannel;
import org.example.message.CriticalAlertMessage;
import org.example.message.Message;
import org.example.message.PromotionMessage;
import org.example.message.ReminderMessage;

public class Main {
    public static void main(String[] args) {
        NotificationChannel emailChannel = new EmailChannel();
        Message criticalAlert = new CriticalAlertMessage(emailChannel);
        criticalAlert.sendMessage("¡Problema en el sistema!");

        NotificationChannel smsChannel = new SmsChannel();
        Message promotion = new PromotionMessage(smsChannel);
        promotion.sendMessage("20% de descuento en tu próxima compra.");

        NotificationChannel pushChannel = new PushChannel();
        Message reminder = new ReminderMessage(pushChannel);
        reminder.sendMessage("Faltan 30 minutos para la cita con tu médico!");
    }
}