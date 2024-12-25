El patrón de diseño **Singleton** es un patrón creacional que asegura que una clase solo tenga una instancia y proporciona un punto de acceso global a esa instancia. Este patrón es especialmente útil cuando necesitamos tener un solo objeto que gestione recursos compartidos, como una conexión a una base de datos, un manejador de configuración, o un servicio de logging.

### Características del patrón Singleton

1. **Única instancia**: Asegura que solo exista una instancia de la clase.
2. **Acceso controlado**: Proporciona un punto de acceso global a la instancia.
3. **Control sobre la creación**: La clase se encarga de crear y gestionar su única instancia.

### Ejemplo en Java (versión básica)

En este ejemplo básico, implementamos un Singleton utilizando un constructor privado y un método estático para devolver la única instancia de la clase.

```java
public class Singleton {
    // Instancia única de la clase, inicializada en null
    private static Singleton instancia;

    // Constructor privado para evitar que se cree una instancia desde fuera de la clase
    private Singleton() {
    }

    // Método para obtener la única instancia de la clase
    public static Singleton getInstance() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }

    public void mostrarMensaje() {
        System.out.println("¡Esta es la única instancia de Singleton!");
    }
}
```

### Uso del Singleton

Para usar el Singleton, simplemente llamamos al método `getInstance()`:

```java
public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        singleton.mostrarMensaje();
    }
}
```

### Explicación del código

1. **Variable `instancia`**: Es una variable estática que almacena la única instancia de la clase `Singleton`.
2. **Constructor privado**: El constructor es privado, lo cual impide que otras clases puedan crear una instancia usando `new Singleton()`.
3. **Método `getInstance()`**: Es un método público y estático que devuelve la única instancia. Si la instancia aún no se ha creado, la crea. Si ya existe, devuelve la instancia existente.

### Problema de Hilos Concurrentes

En la versión básica, este Singleton no es seguro en entornos multihilo. Si varios hilos intentan acceder a `getInstance()` simultáneamente, podrían crear múltiples instancias. Para resolver este problema, podemos implementar el Singleton de una forma segura en hilos.

### Ejemplo Seguro en Hilos (Double-Checked Locking)

Esta versión mejora la seguridad en entornos multihilo mediante una sincronización controlada, también conocida como "double-checked locking":

```java
public class SingletonSeguro {
    // Instancia única de la clase, inicializada en null
    private static volatile SingletonSeguro instancia;

    // Constructor privado
    private SingletonSeguro() {
    }

    // Método para obtener la instancia, con doble verificación de bloqueo
    public static SingletonSeguro getInstance() {
        if (instancia == null) {
            synchronized (SingletonSeguro.class) {
                if (instancia == null) {
                    instancia = new SingletonSeguro();
                }
            }
        }
        return instancia;
    }

    public void mostrarMensaje() {
        System.out.println("¡Esta es la instancia segura de Singleton en entornos multihilo!");
    }
}
```

### Explicación del Double-Checked Locking

1. **`volatile`**: La variable `instancia` es `volatile` para asegurar que los cambios se escriben en la memoria principal y son visibles para todos los hilos.
2. **`synchronized`**: Dentro del bloque `synchronized`, verificamos nuevamente si la instancia es `null` para evitar que se cree otra instancia si ya se ha creado por otro hilo.

### Alternativa: Singleton con Inicialización en la Clase

Una forma sencilla y segura de implementar Singleton en Java es utilizando la inicialización en el momento de la carga de la clase:

```java
public class SingletonEstatico {
    // Instancia única, inicializada en el momento de la carga de la clase
    private static final SingletonEstatico instancia = new SingletonEstatico();

    // Constructor privado
    private SingletonEstatico() {
    }

    // Método para obtener la única instancia de la clase
    public static SingletonEstatico getInstance() {
        return instancia;
    }

    public void mostrarMensaje() {
        System.out.println("¡Esta es la instancia Singleton creada al cargar la clase!");
    }
}
```

En este caso, la instancia se crea cuando la clase se carga en la memoria, asegurando un Singleton seguro y sencillo en aplicaciones multihilo.