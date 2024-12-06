package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.Documento;

public class DocumentoTexto implements Documento {

    @Override
    public void abrir() {
        System.out.println("Abrimos el documento de texto");
    }

    @Override
    public void guardar() {
        System.out.println("Guardamos el documento de texto");
    }

    @Override
    public void cerrar() {
        System.out.println("Cerramos el documento de texto");
    }
}

