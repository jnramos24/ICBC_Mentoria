package com.example.resoluciones_mentoria;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.resoluciones_mentoria.implementation.EmailChannel;
import com.example.resoluciones_mentoria.implementation.PushChannel;
import com.example.resoluciones_mentoria.implementation.SlackChannel;
import com.example.resoluciones_mentoria.implementation.SmsChannel;
import com.example.resoluciones_mentoria.interfaces.NotificationChannel;
import com.example.resoluciones_mentoria.model.CriticalAlertMessage;
import com.example.resoluciones_mentoria.model.PromotionMessage;
import com.example.resoluciones_mentoria.model.ReminderMessage;
import org.apache.logging.log4j.message.Message;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

		public static void main(String[] args) {

            //probar todas las combinaciones en el main

            NotificationChannel emailChannel = new EmailChannel();
            NotificationChannel smsChannel = new SmsChannel();
            NotificationChannel pushChannel = new PushChannel();
            NotificationChannel slackChannel = new SlackChannel();

            CriticalAlertMessage criticalAlertMessage = new CriticalAlertMessage(emailChannel);
            ReminderMessage reminderMessage = new ReminderMessage(smsChannel);
            PromotionMessage promotionMessage = new PromotionMessage(pushChannel);


            criticalAlertMessage.sendMessage("El sistema está caído. Requiere atención inmediata.");
            reminderMessage.sendMessage("Recuerda que mañana tienes una cita.");
            promotionMessage.sendMessage("¡50% de descuento en todos los productos esta semana!");

            PromotionMessage surveyMessage = new PromotionMessage(slackChannel);
            surveyMessage.sendMessage("Participa en nuestra encuesta de satisfacción y gana premios.");
        }
    }