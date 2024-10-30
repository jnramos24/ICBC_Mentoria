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