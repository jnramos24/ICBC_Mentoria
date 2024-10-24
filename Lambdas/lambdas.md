Las funciones lambda en Java permiten escribir métodos de manera más concisa y funcional, aprovechando las interfaces funcionales, que son aquellas que tienen un único método abstracto. Java incluye varias interfaces funcionales en el paquete `java.util.function`, y entre las más comunes están `Supplier`, `Consumer`, `Predicate`, `Function`, y `BiFunction`.

### 1. `Supplier<T>`
La interfaz `Supplier` representa una función que no toma ningún argumento y devuelve un valor del tipo especificado.

```java
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        // Lambda que devuelve un string
        Supplier<String> supplier = () -> "Hello from Supplier!";
        System.out.println(supplier.get());
    }
}
```
En este ejemplo, el `Supplier` no toma argumentos y siempre devuelve el mismo String.

### 2. `Consumer<T>`
La interfaz `Consumer` representa una operación que acepta un único argumento y no devuelve ningún resultado. 

```java
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        // Lambda que imprime un valor
        Consumer<String> consumer = (value) -> System.out.println("Consumed: " + value);
        consumer.accept("Hello from Consumer!");
    }
}
```
En este ejemplo, el `Consumer` acepta un argumento y lo imprime.

### 3. `Predicate<T>`
La interfaz `Predicate` evalúa un valor de tipo `T` y devuelve un valor booleano. Es comúnmente utilizada para realizar validaciones o filtros.

```java
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        // Lambda que comprueba si un número es mayor que 10
        Predicate<Integer> predicate = (value) -> value > 10;
        System.out.println(predicate.test(15)); // true
        System.out.println(predicate.test(5));  // false
    }
}
```
En este ejemplo, el `Predicate` comprueba si un número es mayor que 10.

### 4. `Function<T, R>`
La interfaz `Function` toma un argumento de tipo `T` y devuelve un resultado de tipo `R`.

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        // Lambda que convierte un número entero en su representación en cadena
        Function<Integer, String> function = (value) -> "Number: " + value;
        System.out.println(function.apply(5));  // "Number: 5"
    }
}
```
En este ejemplo, el `Function` toma un entero y lo convierte en un String.

### 5. `BiFunction<T, U, R>`
La interfaz `BiFunction` acepta dos argumentos y devuelve un resultado. Es útil cuando necesitas combinar dos valores para producir un tercero.

```java
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        // Lambda que suma dos números
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;
        System.out.println(biFunction.apply(10, 20));  // 30
    }
}
```
En este ejemplo, el `BiFunction` toma dos enteros y devuelve su suma.

### Resumen de las Interfaces

- `Supplier<T>`: No recibe argumentos y devuelve un valor de tipo `T`.
- `Consumer<T>`: Recibe un argumento de tipo `T` y no devuelve nada.
- `Predicate<T>`: Recibe un argumento de tipo `T` y devuelve un booleano.
- `Function<T, R>`: Recibe un argumento de tipo `T` y devuelve un valor de tipo `R`.
- `BiFunction<T, U, R>`: Recibe dos argumentos de tipo `T` y `U`, y devuelve un valor de tipo `R`.

Estas interfaces funcionales son la base de muchas operaciones en la programación funcional en Java, especialmente cuando se combinan con streams y otras utilidades.

Aquí tienes ejemplos donde cada una de las interfaces (`Supplier`, `Consumer`, `Predicate`, `Function`, `BiFunction`) se aplican en un flujo `Stream` en Java.

### `Supplier` en un Stream

El `Supplier` se usa a menudo para generar valores para un `Stream` de forma perezosa o infinita.

```java
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // Supplier que genera números incrementales
        Supplier<Integer> supplier = new Supplier<>() {
            private int i = 0;
            
            @Override
            public Integer get() {
                return i++;
            }
        };

        // Generar un flujo infinito de números y tomar solo los primeros 5
        Stream.generate(supplier)
              .limit(5)
              .forEach(System.out::println);
    }
}
```
Aquí, el `Supplier` genera números consecutivos y se usa para crear un `Stream` infinito. El `limit(5)` toma los primeros 5 elementos.

### `Consumer` en un Stream

El `Consumer` se puede utilizar en la operación `forEach()` para aplicar una acción a cada elemento del `Stream`.

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Consumer que imprime cada nombre en mayúsculas
        Consumer<String> consumer = (name) -> System.out.println(name.toUpperCase());

        // Aplicar el consumer a cada elemento del stream
        names.stream()
             .forEach(consumer);
    }
}
```
Aquí, el `Consumer` toma cada elemento del flujo, lo transforma a mayúsculas y lo imprime.

### `Predicate` en un Stream

El `Predicate` se usa comúnmente en la operación `filter()` para filtrar elementos que cumplan una condición.

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 5, 10, 15, 20, 25);

        // Predicate que filtra los números mayores a 10
        Predicate<Integer> predicate = (n) -> n > 10;

        // Filtrar el stream de números
        numbers.stream()
               .filter(predicate)
               .forEach(System.out::println);
    }
}
```
Aquí, el `Predicate` filtra los números mayores a 10 y solo esos se imprimen en la salida.

### `Function` en un Stream

El `Function` se aplica con `map()` para transformar los elementos de un `Stream`.

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        // Function que obtiene la longitud de cada nombre
        Function<String, Integer> function = (name) -> name.length();

        // Aplicar la transformación
        names.stream()
             .map(function)
             .forEach(System.out::println);  // Salida: 5, 3, 7
    }
}
```
Aquí, el `Function` transforma cada nombre a su longitud y luego los imprime.

### `BiFunction` en un Stream

El `BiFunction` se puede usar en operaciones de reducción o al combinar elementos de dos `Stream`.

```java
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers1 = Arrays.asList(1, 2, 3);
        List<Integer> numbers2 = Arrays.asList(10, 20, 30);

        // BiFunction que suma dos números
        BiFunction<Integer, Integer, Integer> biFunction = (a, b) -> a + b;

        // Combinar dos listas usando Stream
        List<Integer> result = numbers1.stream()
                .map(n -> biFunction.apply(n, numbers2.get(numbers1.indexOf(n))))
                .toList();

        result.forEach(System.out::println);  // Salida: 11, 22, 33
    }
}
```
Aquí, usamos un `BiFunction` para combinar elementos de dos listas, sumando los valores correspondientes de cada lista.

### Conclusión

- `Supplier`: Genera valores de forma perezosa para el `Stream`.
- `Consumer`: Realiza acciones sobre los elementos en el flujo.
- `Predicate`: Filtra elementos del `Stream` en función de una condición.
- `Function`: Transforma los elementos del flujo.
- `BiFunction`: Permite combinar elementos de dos flujos o listas.