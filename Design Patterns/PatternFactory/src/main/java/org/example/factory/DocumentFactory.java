package org.example.factory;

import org.example.model.Document;
import org.example.model.ImageDocument;
import org.example.model.PDFDocument;
import org.example.model.PresentationDocument;
import org.example.model.SpreadsheetDocument;
import org.example.model.TextDocument;

public class DocumentFactory {
    public static Document createDocument(String type) {
        return switch (type.toLowerCase()) {
            case "text document" -> new TextDocument();
            case "spreadsheet document" -> new SpreadsheetDocument();
            case "presentation document" -> new PresentationDocument();
            case "pdf document" -> new PDFDocument();
            case "image document" -> new ImageDocument();
            default -> throw new IllegalArgumentException("Unknown document type: " + type);
        };
    }
}