package models;

import interfaces.Document;

public class SpreadsheetDocument implements Document {
    @Override
    public void open() {
        System.out.println("Abriendo un documento de hoja de calculo");

    }

    @Override
    public void save() {
        System.out.println("Guardando un documento de hoja de calculo");

    }

    @Override
    public void close() {
        System.out.println("Cerrando un documento de hoja de calculo");

    }
}
