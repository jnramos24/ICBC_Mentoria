En Java, `wait` y `notify` son métodos que se utilizan para coordinar la ejecución de hilos en un entorno de concurrencia. Ambos son métodos de la clase `Object`, lo que significa que están disponibles para cualquier objeto en Java. Estos métodos ayudan a controlar la comunicación entre hilos cuando están esperando un recurso o cuando una condición cambia.

### Conceptos Básicos de `wait` y `notify`

1. **`wait()`**: Hace que el hilo que llama este método entre en un estado de espera hasta que otro hilo llame a `notify` o `notifyAll` en el mismo objeto monitor. Mientras espera, el hilo libera el bloqueo sobre el objeto, permitiendo que otros hilos accedan a él.
  
2. **`notify()`**: Despierta a un hilo que está esperando en el mismo objeto monitor. Si hay varios hilos en espera, se despierta uno arbitrariamente. Sin embargo, el hilo no comienza a ejecutarse inmediatamente; primero debe recuperar el bloqueo del objeto.

3. **`notifyAll()`**: Despierta a todos los hilos que están en espera en el objeto monitor. De los hilos despertados, solo uno puede adquirir el bloqueo y continuar ejecutándose; los otros deberán esperar a que el bloqueo esté disponible.

### Reglas Importantes

- **Monitor**: `wait`, `notify` y `notifyAll` solo pueden usarse dentro de un bloque o método sincronizado. Esto se debe a que el hilo debe poseer el bloqueo del monitor del objeto antes de llamar a estos métodos.
- **Sincronización**: Al usar estos métodos, se debe sincronizar el acceso al recurso compartido para evitar problemas de concurrencia.

### Ejemplo Práctico: Productor-Consumidor

Veamos un ejemplo clásico del problema productor-consumidor, donde un hilo productor genera datos y los coloca en una cola, mientras que un hilo consumidor consume esos datos. `wait` y `notify` se usan aquí para que el consumidor espere cuando la cola está vacía y para que el productor espere cuando la cola está llena.

```java
import java.util.LinkedList;
import java.util.Queue;

class SharedQueue {
    private final Queue<Integer> queue = new LinkedList<>();
    private final int MAX_SIZE = 5;

    public synchronized void produce(int value) throws InterruptedException {
        while (queue.size() == MAX_SIZE) {
            System.out.println("Queue is full. Producer is waiting...");
            wait(); // Espera hasta que el consumidor consuma un elemento
        }

        queue.add(value);
        System.out.println("Produced: " + value);
        notify(); // Notifica al consumidor de que hay un nuevo elemento en la cola
    }

    public synchronized void consume() throws InterruptedException {
        while (queue.isEmpty()) {
            System.out.println("Queue is empty. Consumer is waiting...");
            wait(); // Espera hasta que el productor produzca un elemento
        }

        int value = queue.poll();
        System.out.println("Consumed: " + value);
        notify(); // Notifica al productor de que hay espacio en la cola
    }
}

public class ProducerConsumerExample {
    public static void main(String[] args) {
        SharedQueue sharedQueue = new SharedQueue();

        // Producer thread
        Runnable producerTask = () -> {
            int value = 0;
            while (true) {
                try {
                    sharedQueue.produce(value++);
                    Thread.sleep(500); // Simula el tiempo de producción
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        // Consumer thread
        Runnable consumerTask = () -> {
            while (true) {
                try {
                    sharedQueue.consume();
                    Thread.sleep(1000); // Simula el tiempo de consumo
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread producerThread = new Thread(producerTask, "Producer");
        Thread consumerThread = new Thread(consumerTask, "Consumer");

        producerThread.start();
        consumerThread.start();
    }
}
```

### Explicación del Ejemplo

1. **Método `produce(int value)`**:
   - Si la cola está llena (`MAX_SIZE`), el productor llama a `wait()` y espera hasta que el consumidor elimine un elemento.
   - Si hay espacio en la cola, el productor agrega un valor a la cola y llama a `notify()`, lo que despierta al consumidor para que pueda consumir el nuevo valor.

2. **Método `consume()`**:
   - Si la cola está vacía, el consumidor llama a `wait()` y espera hasta que el productor agregue un elemento.
   - Si hay elementos en la cola, el consumidor elimina un valor y llama a `notify()`, lo que despierta al productor para que pueda agregar nuevos elementos.

3. **Comunicación y Sincronización**:
   - `wait()` permite que un hilo se ponga en espera, liberando el bloqueo para que otros hilos puedan acceder al monitor.
   - `notify()` despierta a uno de los hilos en espera, permitiendo que recupere el bloqueo y continúe.

### Notas Adicionales

- **Diferencia entre `notify()` y `notifyAll()`**: En este ejemplo, `notify()` es suficiente ya que solo hay un productor y un consumidor. Sin embargo, si hubiera múltiples consumidores o productores, `notifyAll()` sería más seguro para despertar a todos los hilos en espera y evitar posibles interbloqueos.

- **`wait()` y `notify()` en Bloques Sincronizados**: Es importante recordar que estos métodos solo se pueden usar dentro de métodos o bloques sincronizados, ya que el hilo necesita poseer el monitor del objeto.