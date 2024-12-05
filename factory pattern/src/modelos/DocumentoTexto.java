package modelos;

import interfaces.Documento;

public class DocumentoTexto implements Documento {
    @Override
    public void abrir() {
        System.out.println("Abriendo documento de texto...");
    }

    @Override
    public void guardar() {
        System.out.println("Guardando documento de texto...");

    }

    @Override
    public void cerrar() {
        System.out.println("Cerrando documento de texto...");

    }
}
