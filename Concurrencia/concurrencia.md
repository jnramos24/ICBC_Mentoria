# Concurrencia

En Java, la concurrencia permite que varias tareas se ejecuten al mismo tiempo dentro de un mismo programa, maximizando el uso de los recursos del sistema y mejorando el rendimiento. Para manejar la concurrencia en Java, se utilizan principalmente hilos (threads), que son las unidades de ejecución concurrente.

### Conceptos Básicos de Concurrencia en Java

1. **Hilos (Threads)**: Son las unidades más pequeñas de ejecución en Java. Cada hilo es un proceso ligero que realiza una tarea independiente. La clase `Thread` en Java permite crear y controlar hilos.

2. **Runnable e Implementación de Threads**:
   - Puedes crear un hilo implementando la interfaz `Runnable` y pasando una instancia de `Runnable` a un objeto `Thread`.
   - También puedes extender la clase `Thread` y sobrescribir el método `run()`.

3. **Sincronización**:
   - La sincronización se usa para evitar que varios hilos accedan y modifiquen datos compartidos al mismo tiempo, evitando condiciones de carrera.
   - Para esto, se utiliza la palabra clave `synchronized` en métodos o bloques de código que deben ser exclusivos para un solo hilo.

4. **Executor Framework**:
   - La API `java.util.concurrent` introdujo el `Executor Framework`, que proporciona un mecanismo más robusto para manejar hilos que directamente con `Thread`.
   - Usa interfaces como `ExecutorService`, `Callable`, y `Future`, que permiten un control más detallado y la capacidad de devolver valores después de la ejecución.

5. **Bloqueo y Monitores**:
   - Java permite bloquear recursos con la palabra clave `synchronized`, pero también ofrece clases como `ReentrantLock`, `ReadWriteLock`, y `Semaphore` en `java.util.concurrent.locks` para un control más avanzado del bloqueo.

6. **Atomicidad**:
   - Java también proporciona clases como `AtomicInteger`, `AtomicBoolean`, etc., en el paquete `java.util.concurrent.atomic` para operaciones que deben ser atómicas.

### Ejemplo de Concurrencia en Java

Aquí tienes un ejemplo simple que muestra la creación de hilos usando `Runnable` y `ExecutorService`:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // Pool de 3 hilos

        // Creación de tareas usando Runnable
        Runnable task1 = () -> {
            System.out.println("Tarea 1 ejecutándose en el hilo: " + Thread.currentThread().getName());
        };

        Runnable task2 = () -> {
            System.out.println("Tarea 2 ejecutándose en el hilo: " + Thread.currentThread().getName());
        };

        // Ejecución de las tareas
        executor.submit(task1);
        executor.submit(task2);

        // Cerrar el Executor para liberar recursos
        executor.shutdown();
    }
}
```

### Beneficios y Desafíos de la Concurrencia

**Beneficios**:
   - **Mejor rendimiento** en aplicaciones que pueden realizar varias tareas a la vez (ej., servidores web).
   - **Uso óptimo de CPU**: Permite aprovechar varios núcleos del procesador.

**Desafíos**:
   - **Condiciones de carrera**: Ocurren cuando múltiples hilos acceden y modifican datos compartidos al mismo tiempo.
   - **Bloqueos**: Los hilos pueden quedar bloqueados si el control de concurrencia no se maneja adecuadamente.
   - **Problemas de sincronización**: Las fallas en la sincronización pueden provocar errores difíciles de depurar.