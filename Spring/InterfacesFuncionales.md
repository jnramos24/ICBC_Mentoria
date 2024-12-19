### Interfaces Funcionales

Las **interfaces funcionales** son un concepto fundamental introducido en Java 8, y son muy útiles en proyectos de Spring, ya que permiten escribir código funcional más limpio y conciso. Veamos sus características principales y cómo utilizarlas con expresiones lambda.

---

### **¿Qué son las interfaces funcionales?**
Una **interfaz funcional** es una interfaz que tiene exactamente un método abstracto. Representa una "función única" que puede ser implementada utilizando una expresión lambda o una referencia a un método.

**Ejemplo básico:**
```java
@FunctionalInterface
public interface Operation {
    int calculate(int a, int b);
}
```

---

### **Características principales de las interfaces funcionales**
1. **Un único método abstracto**:
   - Una interfaz funcional tiene un solo método abstracto, que define el comportamiento esperado.
   - Puede tener métodos `default` o `static`, pero estos no cuentan como métodos abstractos.

2. **Anotación `@FunctionalInterface` (opcional pero recomendada)**:
   - Indica explícitamente que la interfaz es funcional.
   - Garantiza en tiempo de compilación que solo tenga un método abstracto.

   ```java
   @FunctionalInterface
   public interface MyFunctionalInterface {
       void execute();
   }
   ```

3. **Compatibilidad con expresiones lambda y referencias a métodos**:
   - Las lambdas permiten implementar interfaces funcionales de manera breve y legible.

4. **Ejemplos comunes en Java**:
   - `Runnable` (sin parámetros, sin retorno)
   - `Supplier<T>` (sin parámetros, devuelve un valor)
   - `Consumer<T>` (un parámetro, sin retorno)
   - `Function<T, R>` (un parámetro, devuelve un valor)
   - `Predicate<T>` (un parámetro, devuelve un booleano)

---

### **Uso de interfaces funcionales con Lambdas**
Las expresiones lambda son una forma compacta de implementar interfaces funcionales.

**Ejemplo con una interfaz personalizada:**
```java
@FunctionalInterface
public interface Operation {
    int calculate(int a, int b);
}

public class Main {
    public static void main(String[] args) {
        // Lambda implementation
        Operation addition = (a, b) -> a + b;
        Operation multiplication = (a, b) -> a * b;

        System.out.println(addition.calculate(5, 3)); // Output: 8
        System.out.println(multiplication.calculate(5, 3)); // Output: 15
    }
}
```

**Ejemplo con interfaces funcionales estándar de Java:**
```java
import java.util.function.Function;

public class Example {
    public static void main(String[] args) {
        Function<String, Integer> length = str -> str.length();
        System.out.println(length.apply("Spring Framework")); // Output: 17
    }
}
```

---

### **Ventajas de usar interfaces funcionales en Spring**
1. **Reducción de código boilerplate**:
   - Las expresiones lambda y las interfaces funcionales eliminan la necesidad de clases anónimas largas y complejas.

2. **Mayor legibilidad y mantenibilidad**:
   - El código es más corto y su propósito se comunica con mayor claridad.

3. **Facilita el uso de Streams y programación funcional**:
   - Se integran perfectamente con el API de Streams para manipular colecciones y flujos de datos.

4. **Compatibilidad con la programación reactiva**:
   - Spring WebFlux utiliza interfaces funcionales como `Function` y `Consumer` para manejar flujos de datos reactivos.

---

### **Uso práctico en proyectos Spring**
#### **1. `Function` en configuraciones de Bean**
```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Function;

@Configuration
public class ConfigurationExample {
    @Bean
    public Function<String, String> messageProcessor() {
        return message -> "Processed: " + message.toUpperCase();
    }
}
```

#### **2. En controladores reactivos con Spring WebFlux**
```java
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

@Configuration
public class Routes {
    @Bean
    public RouterFunction<ServerResponse> routes() {
        return route(GET("/greeting"), 
            request -> ServerResponse.ok().bodyValue("Hello from Spring WebFlux!"));
    }
}
```

#### **3. Streams y datos en controladores tradicionales**
```java
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<String> getProducts() {
        List<String> products = List.of("Laptop", "Mouse", "Keyboard", "Monitor");
        return products.stream()
                       .filter(product -> product.startsWith("M"))
                       .collect(Collectors.toList());
    }
}
```
---

Aquí presentamos un ejemplo donde se utiliza una **interfaz funcional** para refactorizar y mejorar un código. El objetivo es simplificar un proceso que inicialmente está acoplado y difícil de mantener, transformándolo en un diseño más limpio y reutilizable mediante el uso de expresiones lambda.

### **Código inicial sin interfaz funcional**
Imaginemos un código que procesa diferentes operaciones matemáticas dependiendo de un parámetro:

```java
public class Calculator {

    public int processOperation(String operation, int a, int b) {
        switch (operation) {
            case "add":
                return a + b;
            case "subtract":
                return a - b;
            case "multiply":
                return a * b;
            case "divide":
                if (b == 0) throw new IllegalArgumentException("Division by zero is not allowed");
                return a / b;
            default:
                throw new UnsupportedOperationException("Operation not supported");
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.processOperation("add", 5, 3)); // Output: 8
    }
}
```

#### **Problemas del código inicial:**
1. El método `processOperation` es rígido y difícil de extender. Si necesitas agregar más operaciones, tendrás que modificar el código base, rompiendo el principio de **abierto/cerrado (OCP)**.
2. No es reutilizable ni flexible, ya que las operaciones están "hardcodeadas".

---

### **Código refactorizado utilizando una interfaz funcional**
Refactorizamos el código utilizando una **interfaz funcional** que permite abstraer las operaciones y proporcionar flexibilidad.

```java
import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}

public class Calculator {

    private final Map<String, MathOperation> operations = new HashMap<>();

    public Calculator() {
        // Register operations
        operations.put("add", (a, b) -> a + b);
        operations.put("subtract", (a, b) -> a - b);
        operations.put("multiply", (a, b) -> a * b);
        operations.put("divide", (a, b) -> {
            if (b == 0) throw new IllegalArgumentException("Division by zero is not allowed");
            return a / b;
        });
    }

    public int processOperation(String operation, int a, int b) {
        MathOperation mathOperation = operations.get(operation);
        if (mathOperation == null) {
            throw new UnsupportedOperationException("Operation not supported: " + operation);
        }
        return mathOperation.operate(a, b);
    }

    public void addCustomOperation(String name, MathOperation operation) {
        operations.put(name, operation);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        // Standard operations
        System.out.println(calculator.processOperation("add", 5, 3));       // Output: 8
        System.out.println(calculator.processOperation("multiply", 5, 3)); // Output: 15

        // Add a custom operation
        calculator.addCustomOperation("power", (a, b) -> (int) Math.pow(a, b));
        System.out.println(calculator.processOperation("power", 2, 3));    // Output: 8
    }
}
```

---

### **Ventajas del código refactorizado**
1. **Abierto/cerrado (OCP):**
   - Es fácil agregar nuevas operaciones sin modificar el código base. Solo necesitas registrar una nueva operación con `addCustomOperation`.

2. **Reutilización:**
   - Las operaciones están desacopladas del flujo principal, y la lógica puede ser reutilizada o probada individualmente.

3. **Flexibilidad:**
   - Permite registrar operaciones personalizadas en tiempo de ejecución.

4. **Limpieza:**
   - Se elimina el uso de `switch` o múltiples `if-else`, haciendo que el código sea más legible y manejable.

---

### **Explicación del diseño**
1. **Interfaz funcional `MathOperation`:**
   - Define un contrato genérico para cualquier operación matemática.
   ```java
   @FunctionalInterface
   interface MathOperation {
       int operate(int a, int b);
   }
   ```

2. **Uso de un `Map` para registrar operaciones:**
   - Las operaciones son almacenadas como lambdas en un mapa, permitiendo un acceso rápido y dinámico.

3. **Expresiones lambda:**
   - Las lambdas implementan la interfaz funcional en una forma breve y directa.

4. **Personalización:**
   - La capacidad de agregar nuevas operaciones en tiempo de ejecución (`addCustomOperation`) aumenta la extensibilidad del diseño.

---

Este enfoque es altamente recomendable para aplicaciones basadas en Spring o cualquier otra arquitectura modular, ya que promueve un diseño limpio y extensible.
