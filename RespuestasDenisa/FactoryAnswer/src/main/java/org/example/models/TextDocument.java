package org.example.models;

import org.example.interfaces.IDocument;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TextDocument implements IDocument {

    String content = "Este es el contenido del documento de texto.";
        
    @Override
    public void create() {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el nombre del archivo: ");
        String fileName = scanner.nextLine() + ".txt";

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("El documento ha sido creado exitosamente.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al crear el documento.");
            e.printStackTrace();
        }

        scanner.close();

    }

    @Override
    public void open() {
        System.out.println("El documento ha sido abierto exitosamente.");
    }

    @Override
    public void edit() {
        System.out.println("El documento ha sido editado exitosamente.");
    }

    @Override
    public void save() {
        System.out.println("El documento ha sido guardado exitosamente.");
    }

    @Override
    public void close() {
        System.out.println("Archivo cerrado correctamente");
    }
}
