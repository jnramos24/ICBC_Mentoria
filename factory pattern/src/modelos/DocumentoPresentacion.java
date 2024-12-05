package modelos;

import interfaces.Documento;

public class DocumentoPresentacion implements Documento {
    @Override
    public void abrir() {
        System.out.println("Abriendo presentacion...");
    }

    @Override
    public void guardar() {
        System.out.println("Guardando presentacion...");

    }

    @Override
    public void cerrar() {
        System.out.println("Cerrando presentacion...");

    }
}
