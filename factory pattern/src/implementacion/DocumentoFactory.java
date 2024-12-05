package implementacion;

import interfaces.Documento;
import modelos.DocumentoHojaCalculo;
import modelos.DocumentoPresentacion;
import modelos.DocumentoTexto;

public class DocumentoFactory {
    public static Documento crearDocumento(String tipo){
        switch (tipo) {
            case "texto":
                return new DocumentoTexto();

            case "hojaCalculo":
                return new DocumentoHojaCalculo();

            case "presentacion":
                return new DocumentoPresentacion();

            default:
                throw new IllegalArgumentException("Error al ingresar tipo de documento!");

        }

    }
}
