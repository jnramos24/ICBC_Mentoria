import implementacion.DocumentoFactory;
import interfaces.Documento;

public class Main {
    public static void main(String[] args) {
        Documento texto1 = DocumentoFactory.crearDocumento("texto");

        texto1.abrir();
        texto1.guardar();
        texto1.cerrar();

        Documento hojaExcel = DocumentoFactory.crearDocumento("hojaCalculo");

        hojaExcel.abrir();
        hojaExcel.guardar();
        hojaExcel.cerrar();

        Documento presentacion = DocumentoFactory.crearDocumento("presentacion");

        presentacion.abrir();
        presentacion.guardar();
        presentacion.cerrar();
    }
}