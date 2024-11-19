El Principio de Sustitución de Liskov (LSP, por sus siglas en inglés) es uno de los principios SOLID en diseño de software. Este principio establece que **las clases derivadas deben ser sustituibles por sus clases base sin alterar el correcto funcionamiento del programa**. 

En términos simples, si una clase `B` hereda de una clase `A`, deberíamos poder usar un objeto de la clase `B` donde se espere un objeto de la clase `A`, sin que el programa falle o se comporte de manera inesperada.

El LSP se centra en garantizar que las clases hijas cumplan con el contrato establecido por sus clases base. Esto implica que no deben romper las expectativas ni introducir restricciones adicionales.

### Reglas clave del LSP:
1. **Precondiciones no deben ser fortalecidas** en la subclase.
2. **Postcondiciones no deben ser debilitadas** en la subclase.
3. Las invariantes de la clase base deben mantenerse en las subclases.

---

### Caso incorrecto (violación del LSP)

Supongamos que tienes una clase base `Bird` y dos subclases: `Sparrow` (que puede volar) y `Penguin` (que no puede volar). Si implementamos un método `fly()` en la clase base, estaremos violando el LSP porque no todas las aves pueden volar.

```java
class Bird {
    public void fly() {
        System.out.println("I am flying!");
    }
}

class Sparrow extends Bird {
    @Override
    public void fly() {
        System.out.println("Sparrow flying!");
    }
}

class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins cannot fly!");
    }
}

public class Main {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        sparrow.fly(); // Output: Sparrow flying!

        Bird penguin = new Penguin();
        penguin.fly(); // Lanza una excepción: UnsupportedOperationException
    }
}
```

Aquí, al intentar usar un objeto `Penguin` como un `Bird`, se produce un comportamiento inesperado (una excepción), lo que viola el LSP. La clase base `Bird` establece la expectativa de que todas las subclases puedan volar, pero no es cierto para `Penguin`.

---

### Solución (respetando el LSP)

En lugar de definir un método `fly()` en la clase base, podemos introducir una interfaz específica para las aves que pueden volar. Esto elimina la expectativa de que todas las aves puedan volar y respeta el LSP.

```java
// Clase base para todas las aves
abstract class Bird {
    public abstract void eat();
}

// Interfaz específica para aves que pueden volar
interface Flyable {
    void fly();
}

// Subclase para gorriones (pueden volar)
class Sparrow extends Bird implements Flyable {
    @Override
    public void eat() {
        System.out.println("Sparrow is eating!");
    }

    @Override
    public void fly() {
        System.out.println("Sparrow is flying!");
    }
}

// Subclase para pingüinos (no pueden volar)
class Penguin extends Bird {
    @Override
    public void eat() {
        System.out.println("Penguin is eating!");
    }
}

public class Main {
    public static void main(String[] args) {
        Bird sparrow = new Sparrow();
        sparrow.eat(); // Output: Sparrow is eating!

        if (sparrow instanceof Flyable) {
            ((Flyable) sparrow).fly(); // Output: Sparrow is flying!
        }

        Bird penguin = new Penguin();
        penguin.eat(); // Output: Penguin is eating!
    }
}
```

---

### Explicación

1. **Clase base `Bird`:** Define un comportamiento común para todas las aves (por ejemplo, `eat()`).
2. **Interfaz `Flyable`:** Introduce el método `fly()` solo para las aves que pueden volar.
3. **Subclases `Sparrow` y `Penguin`:**
   - `Sparrow` implementa `Flyable` porque puede volar.
   - `Penguin` no implementa `Flyable`, ya que no puede volar, y no intenta sobrescribir el método `fly()`.

Al usar este diseño, respetamos el LSP porque las subclases no violan las expectativas de la clase base ni introducen comportamientos inesperados.