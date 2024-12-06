package com.example.resoluciones_mentoria.utils;

import com.example.resoluciones_mentoria.interfaces.Documento;
import com.example.resoluciones_mentoria.model.DocumentoHojaCalculo;
import com.example.resoluciones_mentoria.model.DocumentoPresentacion;
import com.example.resoluciones_mentoria.model.DocumentoTexto;

public class DocumentFactory {

    public static Documento createDocument(String tipo) {

        switch (tipo.toLowerCase()) {
            case "texto":
                return new DocumentoTexto();
            case "hojaCalculo":
                return new DocumentoHojaCalculo();
            case "presentacion":
                return new DocumentoPresentacion();
            default:
                throw new IllegalArgumentException("Tipo de documento no válido: " + tipo);
        }
    }
}