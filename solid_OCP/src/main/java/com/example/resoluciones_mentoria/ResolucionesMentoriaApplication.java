package com.example.resoluciones_mentoria;

import com.example.resoluciones_mentoria.model.EmailNotification;
import com.example.resoluciones_mentoria.model.NotificationSender;
import com.example.resoluciones_mentoria.model.SMSNotification;
import com.example.resoluciones_mentoria.utils.Notification;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolucionesMentoriaApplication.class, args);

		NotificationSender notificationSender = new NotificationSender();
		Notification notificationSMS = new SMSNotification();
		Notification notificationEmail = new EmailNotification();

		notificationSender.sendNotification(notificationSMS);
		System.out.println();
		notificationSender.sendNotification(notificationEmail);
		System.out.println();
	}

}
