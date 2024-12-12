package org.example.utils;

import org.example.interfaces.IDocument;
import org.example.models.PresentationDocument;
import org.example.models.SpreadSheetDocument;
import org.example.models.TextDocument;

public class DocumentFactory {
    public static IDocument createDocument(String type){

        return switch (type.toLowerCase()) {
            case "text" -> new TextDocument();
            case "excel" -> new SpreadSheetDocument();
            case "ppt" -> new PresentationDocument();
            default -> throw new IllegalArgumentException("Tipo de documento no reconocido");
        };
}
}
