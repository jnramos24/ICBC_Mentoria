package com.example.resoluciones_mentoria.model;

import com.example.resoluciones_mentoria.interfaces.Documento;

public class DocumentoHojaCalculo implements Documento {

    @Override
    public void abrir() {
        System.out.println("Abrimos las hojas de cálculo");
    }

    @Override
    public void guardar() {
        System.out.println("Guardamos las hojas de cálculo");
    }

    @Override
    public void cerrar() {
        System.out.println("Cerramos las hojas de cálculo");
    }
}

