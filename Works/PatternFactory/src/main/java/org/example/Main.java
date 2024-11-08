package org.example;

import org.example.factory.DocumentFactory;
import org.example.model.Document;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("---- TEXT DOCUMENT --------");
            Document textDocument = DocumentFactory.createDocument("Text Document");
            textDocument.open();
            textDocument.save();
            textDocument.close();

            System.out.println("\n---- SPREADSHEET DOCUMENT --------");
            Document spreadsheetDocument = DocumentFactory.createDocument("Spreadsheet Document");
            spreadsheetDocument.open();
            spreadsheetDocument.save();
            spreadsheetDocument.close();

            System.out.println("\n---- PRESENTATION DOCUMENT --------");
            Document presentationDocument = DocumentFactory.createDocument("Presentation Document");
            presentationDocument.open();
            presentationDocument.save();
            presentationDocument.close();

            System.out.println("\n---- PDF DOCUMENT --------");
            Document pdfDocument = DocumentFactory.createDocument("PDF Document");
            pdfDocument.open();
            pdfDocument.save();
            pdfDocument.close();

            System.out.println("\n---- IMAGE DOCUMENT --------");
            Document imageDocument = DocumentFactory.createDocument("Image Document");
            imageDocument.open();
            imageDocument.save();
            imageDocument.close();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}