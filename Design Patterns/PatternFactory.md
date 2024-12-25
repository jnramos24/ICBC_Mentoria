## TEORÍA

El patrón de diseño **Factory** (o **Factory Method**) es un patrón creacional que permite crear objetos sin especificar la clase exacta del objeto que se creará. En lugar de instanciar objetos directamente, el patrón Factory proporciona un método para crear instancias de una clase a partir de una interfaz común. Esto permite delegar la creación de objetos a subclases o a una clase dedicada (la "fábrica").

### Ventajas del patrón Factory
1. **Desacoplamiento**: El cliente no necesita conocer las clases concretas que va a instanciar.
2. **Flexibilidad**: Permite agregar nuevas clases de productos sin modificar el código del cliente.
3. **Responsabilidad única**: Centraliza la lógica de creación de objetos en una clase.

### Ejemplo en Java

Imaginemos que tenemos una interfaz `Transporte` y varias implementaciones de transporte (`Coche`, `Bicicleta` y `Avion`). El patrón Factory nos permitirá instanciar cualquiera de estas clases sin exponer sus detalles de implementación.

1. **Definimos la interfaz `Transporte`**:

   ```java
   public interface Transporte {
       void mover();
   }
   ```

2. **Implementaciones concretas de `Transporte`**:

   ```java
   public class Coche implements Transporte {
       @Override
       public void mover() {
           System.out.println("El coche está conduciendo en la carretera.");
       }
   }

   public class Bicicleta implements Transporte {
       @Override
       public void mover() {
           System.out.println("La bicicleta está circulando por el carril bici.");
       }
   }

   public class Avion implements Transporte {
       @Override
       public void mover() {
           System.out.println("El avión está volando en el cielo.");
       }
   }
   ```

3. **Clase Factory**: Creamos una clase `TransporteFactory` con un método que devuelve instancias de `Transporte` según un tipo específico.

   ```java
   public class TransporteFactory {
       public static Transporte crearTransporte(String tipo) {
           switch (tipo.toLowerCase()) {
               case "coche":
                   return new Coche();
               case "bicicleta":
                   return new Bicicleta();
               case "avion":
                   return new Avion();
               default:
                   throw new IllegalArgumentException("Tipo de transporte desconocido: " + tipo);
           }
       }
   }
   ```

4. **Uso del patrón Factory**: En el cliente, usamos `TransporteFactory` para crear instancias de `Transporte` sin conocer sus clases concretas.

   ```java
   public class Main {
       public static void main(String[] args) {
           Transporte coche = TransporteFactory.crearTransporte("coche");
           coche.mover(); // Salida: El coche está conduciendo en la carretera.

           Transporte bicicleta = TransporteFactory.crearTransporte("bicicleta");
           bicicleta.mover(); // Salida: La bicicleta está circulando por el carril bici.

           Transporte avion = TransporteFactory.crearTransporte("avion");
           avion.mover(); // Salida: El avión está volando en el cielo.
       }
   }
   ```

### Explicación del código

1. **Interfaz `Transporte`**: Define el método `mover()` que será implementado por todas las clases de transporte.
2. **Clases concretas (`Coche`, `Bicicleta`, `Avion`)**: Implementan la interfaz `Transporte` y su método `mover()` con su comportamiento específico.
3. **`TransporteFactory`**: La clase de fábrica tiene el método `crearTransporte()`, que decide qué tipo de objeto `Transporte` crear en función del parámetro `tipo`. Esto centraliza la lógica de creación y permite cambiar o ampliar tipos de transporte sin modificar el código cliente.
4. **Cliente (`Main`)**: En el cliente, simplemente se llama a `TransporteFactory.crearTransporte()` con el tipo deseado y se obtiene un objeto de tipo `Transporte`.

### Ventajas del Patrón Factory en este Ejemplo

- **Flexibilidad**: Podemos agregar nuevos tipos de transporte (como "Tren" o "Barco") sin modificar el código del cliente, solo añadiéndolos a la fábrica.
- **Desacoplamiento**: El cliente (`Main`) no necesita saber los detalles de implementación de cada tipo de transporte, solo llama al método `crearTransporte()` y obtiene una instancia que puede usar a través de la interfaz común `Transporte`.


## EJEMPLO DE PROBLEMA

Imagina que estás desarrollando una aplicación de logística que gestiona distintos tipos de envíos (marítimo, terrestre, aéreo). Cada tipo de envío tiene características específicas: el transporte marítimo puede requerir contenedores grandes, el aéreo tiene restricciones de peso, y el terrestre tiene que considerar el tiempo de tránsito en carretera. 

En la aplicación, tienes una interfaz `Envio` y varias clases concretas que representan cada tipo de envío (`EnvioMaritimo`, `EnvioTerrestre`, `EnvioAereo`). Cada tipo de envío tiene un método `calcularCosto()` que implementa el cálculo de costos de forma diferente.

**Problema**: En tu aplicación, en diferentes puntos (páginas o servicios), necesitas crear objetos de tipo `Envio`, pero no quieres que el código cliente (como los controladores o servicios de la aplicación) sepa ni se encargue de instanciar cada tipo específico de envío. Esto puede hacer que el código sea difícil de mantener, y cualquier cambio en las clases de envío requeriría modificar el código en múltiples lugares.

### Solución con el Patrón Factory

Con el patrón Factory, puedes centralizar la lógica de creación en una clase fábrica (`EnvioFactory`). De este modo, el cliente solo llama al método de fábrica y solicita el tipo de envío que necesita sin preocuparse de los detalles específicos de cada clase.

### Implementación de la Solución

1. **Interfaz `Envio`**: Define el comportamiento común de todos los envíos.

   ```java
   public interface Envio {
       double calcularCosto();
   }
   ```

2. **Clases de Envío Concretas**: Cada clase concreta implementa la interfaz y define su propio cálculo de costo.

   ```java
   public class EnvioMaritimo implements Envio {
       @Override
       public double calcularCosto() {
           return 500.0; // Costo fijo para envíos marítimos
       }
   }

   public class EnvioTerrestre implements Envio {
       @Override
       public double calcularCosto() {
           return 200.0; // Costo fijo para envíos terrestres
       }
   }

   public class EnvioAereo implements Envio {
       @Override
       public double calcularCosto() {
           return 1000.0; // Costo fijo para envíos aéreos
       }
   }
   ```

3. **Clase Fábrica (`EnvioFactory`)**: Se encarga de crear instancias de las clases de envío según el tipo solicitado.

   ```java
   public class EnvioFactory {
       public static Envio crearEnvio(String tipo) {
           switch (tipo.toLowerCase()) {
               case "maritimo":
                   return new EnvioMaritimo();
               case "terrestre":
                   return new EnvioTerrestre();
               case "aereo":
                   return new EnvioAereo();
               default:
                   throw new IllegalArgumentException("Tipo de envío desconocido: " + tipo);
           }
       }
   }
   ```

4. **Cliente**: En el código cliente, puedes crear envíos de manera sencilla sin preocuparte por la clase específica que se usa.

   ```java
   public class Main {
       public static void main(String[] args) {
           Envio envio1 = EnvioFactory.crearEnvio("maritimo");
           System.out.println("Costo del envío marítimo: " + envio1.calcularCosto());

           Envio envio2 = EnvioFactory.crearEnvio("terrestre");
           System.out.println("Costo del envío terrestre: " + envio2.calcularCosto());

           Envio envio3 = EnvioFactory.crearEnvio("aereo");
           System.out.println("Costo del envío aéreo: " + envio3.calcularCosto());
       }
   }
   ```

### ¿Cómo Soluciona el Problema?

1. **Centraliza la Lógica de Creación**: En lugar de que cada parte de la aplicación se encargue de instanciar `EnvioMaritimo`, `EnvioTerrestre` o `EnvioAereo`, todo pasa a través de `EnvioFactory`. Esto facilita el mantenimiento y el control de la creación de instancias.

2. **Flexibilidad para Nuevos Tipos de Envío**: Si necesitas agregar un nuevo tipo de envío, por ejemplo, `EnvioFerrocarril`, solo tienes que agregar una nueva clase `EnvioFerrocarril` y actualizar `EnvioFactory`, sin cambiar nada en el código cliente.

3. **Desacoplamiento del Cliente**: El cliente no necesita saber los detalles de las clases concretas de `Envio`. Solo interactúa con la interfaz común `Envio`, lo que hace que el código sea más modular y menos dependiente de los detalles de implementación.


## EJERCICIO: Sistema de Documentos de Oficina

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