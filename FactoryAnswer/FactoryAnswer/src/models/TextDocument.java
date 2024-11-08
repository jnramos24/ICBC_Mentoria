package models;

import interfaces.Document;

public class TextDocument implements Document {
    @Override
    public void open() {
        System.out.println("Abriendo un documento de texto");

    }

    @Override
    public void save() {
        System.out.println("Guardando un documento de texto");

    }

    @Override
    public void close() {
        System.out.println("Cerrando un documento de texto");

    }
}
