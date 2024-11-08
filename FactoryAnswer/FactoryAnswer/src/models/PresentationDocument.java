package models;

import interfaces.Document;

public class PresentationDocument implements Document {
    @Override
    public void open() {
        System.out.println("Abriendo un documento de presentacion");

    }

    @Override
    public void save() {
        System.out.println("guardando un documento de presentacion");

    }

    @Override
    public void close() {
        System.out.println("Cerrando un documento de presentacion");

    }
}
