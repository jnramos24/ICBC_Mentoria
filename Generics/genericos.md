# Genéricos

Los **genéricos** en Java permiten crear clases, interfaces y métodos que puedan operar sobre cualquier tipo de objeto, proporcionando un nivel adicional de flexibilidad y reutilización sin perder seguridad en el tipo. Esto es particularmente útil para estructuras de datos (como listas, colas, pilas, etc.), ya que te permite manejar diferentes tipos de datos sin necesidad de duplicar código.

### Beneficios de los genéricos:
1. **Seguridad de tipos en tiempo de compilación**: Los errores de tipo se detectan en tiempo de compilación, evitando problemas en tiempo de ejecución.
2. **Reutilización del código**: Puedes escribir clases, interfaces o métodos que funcionen con cualquier tipo de dato.
3. **Eliminación de castings innecesarios**: No es necesario hacer conversiones de tipo cuando se usa genéricos.

### Sintaxis básica de genéricos
Los genéricos se definen usando corchetes angulares `<>` con un tipo de parámetro. Por convención, se usa una sola letra para los nombres de los parámetros de tipo.

- **`T`** es para "Tipo" (Type)
- **`E`** es para "Elemento" (Element)
- **`K`** es para "Clave" (Key)
- **`V`** es para "Valor" (Value)

### Ejemplo de clase genérica

```java
// Clase genérica que acepta cualquier tipo T
public class Box<T> {
    private T item;

    public void setItem(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }
}
```

En este caso, `Box<T>` puede ser usada para almacenar cualquier tipo de objeto.

```java
Box<String> stringBox = new Box<>();
stringBox.setItem("Hola");
System.out.println(stringBox.getItem());

Box<Integer> integerBox = new Box<>();
integerBox.setItem(10);
System.out.println(integerBox.getItem());
```

### Métodos genéricos

Los métodos también pueden ser genéricos. Aquí está un ejemplo de un método que acepta y devuelve cualquier tipo de objeto.

```java
public class Util {
    public static <T> void print(T item) {
        System.out.println(item);
    }
}
```

Este método puede ser llamado con cualquier tipo:

```java
Util.print(10);
Util.print("Hola");
```

### Bounded Types (Restricción de Tipos)

A veces necesitas restringir los tipos que pueden ser usados en genéricos. Puedes usar `extends` para imponer restricciones, como por ejemplo, aceptar solo subtipos de una clase específica.

```java
public class NumberBox<T extends Number> {
    private T number;

    public void setNumber(T number) {
        this.number = number;
    }

    public T getNumber() {
        return number;
    }
}
```

Aquí, `NumberBox` solo acepta tipos que son subclases de `Number`, como `Integer`, `Double`, etc.

```java
NumberBox<Integer> intBox = new NumberBox<>();
NumberBox<Double> doubleBox = new NumberBox<>();
```

### Wildcards (`?`)

El símbolo `?` se usa como un comodín para representar cualquier tipo. Esto es útil cuando no necesitas especificar el tipo exacto, pero quieres permitir varios tipos dentro de ciertos límites.

- **`? extends T`**: Acepta cualquier clase que sea un subtipo de `T`.
- **`? super T`**: Acepta cualquier clase que sea un supertipo de `T`.

```java
public void processNumbers(List<? extends Number> numbers) {
    for (Number num : numbers) {
        System.out.println(num);
    }
}
```

En este caso, `processNumbers` puede recibir una lista de cualquier tipo que extienda `Number`, como `Integer` o `Double`.

### Conclusión

Los genéricos son una poderosa herramienta en Java que proporciona mayor flexibilidad y seguridad en el tipo, permitiendo escribir código más reutilizable y menos propenso a errores. Son ampliamente utilizados en las bibliotecas estándar de Java, como en las colecciones (`List<T>`, `Map<K, V>`, etc.).