package org.example.models;

import org.example.interfaces.IDocument;

public class SpreadSheetDocument implements IDocument {

    @Override
    public void create() {
        System.out.println("El documento de tipo SpreadSheet ha sido creado exitosamente.");

    }

    @Override
    public void open() {
        System.out.println("El documento de tipo SpreadSheet ha sido abierto exitosamente.");
    }

    @Override
    public void edit() {
        System.out.println("El documento de tipo SpreadSheet ha sido editado exitosamente.");
    }

    @Override
    public void save() {
        System.out.println("El documento de tipo SpreadSheet ha sido guardado exitosamente.");
    }

    @Override
    public void close() {
        System.out.println("El documento de tipo SpreadSheet ha sido cerrado exitosamente.");
    }
}