### Ejercicio: Sistema de Documentos de Oficina

**Descripción:** En una aplicación de oficina, se necesita un sistema para manejar diferentes tipos de documentos: `DocumentoTexto`, `DocumentoHojaCalculo` y `DocumentoPresentacion`. Cada tipo de documento debe tener métodos específicos para abrir, guardar y cerrar el documento.

**Objetivo:** Implementar un sistema de creación de documentos utilizando el patrón Factory, de forma que la aplicación pueda generar documentos de diferentes tipos sin que el código del cliente conozca los detalles específicos de cada tipo de documento.

### Requerimientos del Ejercicio

1. **Crear una interfaz `Documento`** que tenga los siguientes métodos:
   - `abrir()`: Método para abrir el documento.
   - `guardar()`: Método para guardar el documento.
   - `cerrar()`: Método para cerrar el documento.

2. **Crear clases concretas** para cada tipo de documento (`DocumentoTexto`, `DocumentoHojaCalculo`, `DocumentoPresentacion`) que implementen la interfaz `Documento`. Cada clase debe tener su propia implementación de los métodos `abrir`, `guardar` y `cerrar`, con una salida que indique el tipo de documento y la acción realizada (por ejemplo, "Abriendo documento de texto").

3. **Crear una clase fábrica (`DocumentoFactory`)** que tenga un método `crearDocumento(String tipo)`. Este método debe devolver una instancia de `Documento` según el tipo solicitado (`texto`, `hoja_calculo` o `presentacion`). Si el tipo no es válido, debe lanzar una excepción.

4. **Escribir un programa principal** que solicite al usuario el tipo de documento que quiere crear, use `DocumentoFactory` para crear el documento y luego ejecute los métodos `abrir`, `guardar` y `cerrar` en el documento creado.

### Reglas del Ejercicio

- Los estudiantes deben aplicar el patrón Factory correctamente, de forma que el cliente solo use la fábrica (`DocumentoFactory`) para crear instancias de `Documento`.
- Deben manejar excepciones en caso de que el tipo de documento solicitado no sea válido.

### Ejemplo de Salida Esperada

Si el usuario elige crear un documento de texto, la salida podría ser algo como:

```
Creando documento de tipo: texto
Abriendo documento de texto
Guardando documento de texto
Cerrando documento de texto
```

### Extensión del Ejercicio (Opcional)

1. Agregar nuevos tipos de documentos, como `DocumentoPDF` o `DocumentoImagen`.
2. Crear una interfaz gráfica básica (opcional) donde el usuario pueda seleccionar el tipo de documento.

Este ejercicio nos permite trabajar con interfaces, polimorfismo y excepciones, y aplicar el patrón Factory en un caso de uso realista. Además, fomenta el uso de buenas prácticas en el diseño de software y el desacoplamiento del código.