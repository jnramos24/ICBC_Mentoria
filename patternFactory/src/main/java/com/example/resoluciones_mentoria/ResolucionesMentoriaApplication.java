package com.example.resoluciones_mentoria;

import com.example.resoluciones_mentoria.interfaces.Documento;
import com.example.resoluciones_mentoria.utils.DocumentFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ResolucionesMentoriaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ResolucionesMentoriaApplication.class, args);

		Scanner scanner = new Scanner(System.in);
		System.out.println("Ingrese el tipo de documento a crear (texto, hojaCalculo, presentacion): ");
		String tipo = scanner.nextLine();

		try {
			System.out.println("Creando documento de tipo: " + tipo);

			Documento documento = DocumentFactory.createDocument(tipo);
			documento.abrir();
			documento.guardar();
			documento.cerrar();

		} catch (IllegalArgumentException e) {
			System.err.println("Error: " + e.getMessage());

		} finally {
			scanner.close();

		}
	}
}
