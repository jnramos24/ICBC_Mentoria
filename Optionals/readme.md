En Java, la clase `Optional` es una herramienta que se utiliza para manejar valores que pueden ser nulos de una manera más segura y menos propensa a errores. Introducida en Java 8, evita el uso directo de valores nulos, reduciendo el riesgo de excepciones como `NullPointerException`.

Un `Optional<T>` es un contenedor que puede contener un valor de tipo `T` o estar vacío, y proporciona métodos para manipular este valor de forma segura.

### Creación de un Optional

Puedes crear un `Optional` de varias maneras:

1. **`Optional.of(value)`**: Crea un `Optional` que contiene un valor, pero si el valor es nulo, lanzará una excepción.
2. **`Optional.ofNullable(value)`**: Crea un `Optional` que contiene un valor si no es nulo, y uno vacío si el valor es nulo.
3. **`Optional.empty()`**: Crea un `Optional` vacío.

### Ejemplo básico

```java
import java.util.Optional;

public class OptionalExample {

    public static void main(String[] args) {
        // Crear un Optional con un valor no nulo
        Optional<String> optionalWithValue = Optional.of("Hello, World!");
        
        // Crear un Optional a partir de un valor que puede ser nulo
        String nullableValue = null;
        Optional<String> optionalNullable = Optional.ofNullable(nullableValue);

        // Crear un Optional vacío
        Optional<String> emptyOptional = Optional.empty();
        
        // Imprimir los Optional
        System.out.println("Optional con valor: " + optionalWithValue);
        System.out.println("Optional con valor nulo: " + optionalNullable);
        System.out.println("Optional vacío: " + emptyOptional);
    }
}
```

### Métodos útiles de Optional

1. **`isPresent()`**: Verifica si el `Optional` contiene un valor.
   
   ```java
   if (optionalWithValue.isPresent()) {
       System.out.println("El valor es: " + optionalWithValue.get());
   }
   ```

2. **`ifPresent(Consumer)`**: Ejecuta una acción si el `Optional` contiene un valor.
   
   ```java
   optionalWithValue.ifPresent(value -> System.out.println("Valor: " + value));
   ```

3. **`orElse(T other)`**: Devuelve el valor contenido en el `Optional` si está presente, o un valor por defecto si está vacío.
   
   ```java
   String result = optionalNullable.orElse("Valor por defecto");
   System.out.println(result);
   ```

4. **`orElseGet(Supplier)`**: Devuelve el valor del `Optional` si está presente, o invoca un `Supplier` para obtener un valor por defecto.
   
   ```java
   String result = optionalNullable.orElseGet(() -> "Generado por Supplier");
   System.out.println(result);
   ```

5. **`orElseThrow()`**: Lanza una excepción si el `Optional` está vacío.
   
   ```java
   try {
       String result = optionalNullable.orElseThrow();
   } catch (NoSuchElementException e) {
       System.out.println("No se encontró valor en el Optional");
   }
   ```

6. **`map(Function)`**: Si el valor está presente, aplica una función y devuelve un nuevo `Optional` con el resultado. Si está vacío, devuelve un `Optional` vacío.
   
   ```java
   Optional<Integer> length = optionalWithValue.map(String::length);
   System.out.println("Longitud del String: " + length.orElse(0));
   ```

7. **`flatMap(Function)`**: Similar a `map`, pero se usa cuando la función devuelve un `Optional` en lugar de un valor directo.

   ```java
   Optional<String> optionalFlatMapped = optionalWithValue.flatMap(value -> Optional.of(value.toUpperCase()));
   optionalFlatMapped.ifPresent(System.out::println);
   ```

### Ejemplo completo con `Optional`

```java
import java.util.Optional;

public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public static void main(String[] args) {
        User userWithMail = new User("John", "john@example.com");
        User userWithoutMail = new User("Jane", null);

        // Si tiene email, imprimir
        userWithMail.getEmail().ifPresent(email -> System.out.println("Email de John: " + email));

        // Si no tiene email, usar valor por defecto
        String email = userWithoutMail.getEmail().orElse("No tiene email");
        System.out.println("Email de Jane: " + email);
    }
}
```

En este ejemplo, `User` tiene un método `getEmail` que devuelve un `Optional<String>`. Si el email no está presente, el código puede manejar esta situación de manera segura, sin riesgo de lanzar una `NullPointerException`.