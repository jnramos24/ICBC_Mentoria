package org.example;

import org.example.interfaces.IDocument;
import org.example.models.TextDocument;
import org.example.utils.DocumentFactory;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Introduce el tipo de archivo: ");
        String fileName = scanner.nextLine();
        IDocument document = DocumentFactory.createDocument(fileName);

        document.create();

        scanner.close();
    }
}