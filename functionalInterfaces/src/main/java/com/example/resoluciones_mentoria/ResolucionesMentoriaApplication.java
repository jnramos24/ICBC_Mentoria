package com.example.resoluciones_mentoria;

import com.example.resoluciones_mentoria.service.ComissionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolucionesMentoriaApplication.class, args);

		ComissionService comissionService = new ComissionService();

		System.out.println("Senior, 4000: " + comissionService.comissionCalculator("Senior", 4000));
		System.out.println("Junior, 3000: " + comissionService.comissionCalculator("Junior", 3000));
		System.out.println("Trainee, 2000: " + comissionService.comissionCalculator("Trainee", 2000));
		System.out.println("Desconocido, 1000: " + comissionService.comissionCalculator("Desconocido", 1000));
	}
	}


