package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.Documento;

public class DocumentoPresentacion implements Documento {

    @Override
    public void abrir() {
        System.out.println("Abrimos la presentación");
    }

    @Override
    public void guardar() {
        System.out.println("Guardamos la presentación");
    }

    @Override
    public void cerrar() {
        System.out.println("Cerramos la presentación");
    }
}

