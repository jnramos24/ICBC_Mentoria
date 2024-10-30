### Ejemplo de Problema

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