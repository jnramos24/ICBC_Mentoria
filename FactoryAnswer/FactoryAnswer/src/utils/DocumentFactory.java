package utils;

import interfaces.Document;
import models.PresentationDocument;
import models.SpreadsheetDocument;
import models.TextDocument;

public class DocumentFactory {

    public static Document createDocument(String type){

        switch (type.toUpperCase()){
            case "TEXT":
                return new TextDocument();
            case "CALCULO":
                return new SpreadsheetDocument();
            case "PRESENTACION":
                return new PresentationDocument();
            default:
                throw new IllegalArgumentException("Tipo de documento no reconocido");
        }
    }
}
