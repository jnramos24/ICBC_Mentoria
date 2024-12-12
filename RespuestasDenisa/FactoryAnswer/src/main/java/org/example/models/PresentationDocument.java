package org.example.models;

import org.example.interfaces.IDocument;

public class PresentationDocument implements IDocument {
    @Override
    public void create() {
        System.out.println("El documento de tipo Presentation ha sido creado exitosamente.");
    }

    @Override
    public void open() {
        System.out.println("El documento de tipo Presentation ha sido creado exitosamente.");
    }

    @Override
    public void edit() {
        System.out.println("El documento de tipo Presentation ha sido editado exitosamente.");
    }

    @Override
    public void save() {
        System.out.println("El documento de tipo Presentation ha sido guardado exitosamente.");
    }

    @Override
    public void close() {
        System.out.println("El documento de tipo Presentation ha sido cerrado exitosamente.");
    }
}
