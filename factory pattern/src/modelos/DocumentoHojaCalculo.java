package modelos;

import interfaces.Documento;

public class DocumentoHojaCalculo implements Documento {
    @Override
    public void abrir() {
        System.out.println("Abriendo hoja de calculo...");
    }

    @Override
    public void guardar() {
        System.out.println("Guardando hoja de calculo...");

    }

    @Override
    public void cerrar() {
        System.out.println("Cerrando hoja de calculo...");

    }
}
