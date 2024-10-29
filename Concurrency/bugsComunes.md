Al trabajar con hilos (`Thread`) y tareas concurrentes en Java, pueden surgir varios errores comunes que afectan el rendimiento y la estabilidad del programa. Aquí te explico algunos de los errores más comunes, incluidos los *deadlocks*, y cómo evitarlos.

### **Deadlock (Bloqueo Mutuo)**

Un *deadlock* ocurre cuando dos o más hilos quedan bloqueados esperando recursos que están retenidos por otros hilos. Esto provoca que ambos hilos queden en un estado de espera permanente, ya que ninguno puede continuar sin el recurso que posee el otro.

**Ejemplo de Deadlock**:

```java
class DeadlockExample {
    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void metodo1() {
        synchronized (lock1) {
            System.out.println("Hilo " + Thread.currentThread().getName() + " bloqueó lock1");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock2) {
                System.out.println("Hilo " + Thread.currentThread().getName() + " bloqueó lock2");
            }
        }
    }

    public void metodo2() {
        synchronized (lock2) {
            System.out.println("Hilo " + Thread.currentThread().getName() + " bloqueó lock2");
            try { Thread.sleep(100); } catch (InterruptedException e) {}
            synchronized (lock1) {
                System.out.println("Hilo " + Thread.currentThread().getName() + " bloqueó lock1");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        DeadlockExample example = new DeadlockExample();

        Thread hilo1 = new Thread(example::metodo1, "Hilo 1");
        Thread hilo2 = new Thread(example::metodo2, "Hilo 2");

        hilo1.start();
        hilo2.start();
    }
}
```

**Explicación**:
- Aquí, `hilo1` bloquea `lock1` y espera `lock2`, mientras que `hilo2` bloquea `lock2` y espera `lock1`, lo que causa un *deadlock*.
  
**Solución**:
- Para evitar *deadlocks*, organiza el orden en el que los hilos adquieren los bloqueos. Por ejemplo, asegúrate de que todos los hilos bloqueen primero `lock1` y luego `lock2`, evitando un orden de bloqueo circular.

### **Race Condition (Condición de Carrera)**

Una condición de carrera ocurre cuando dos o más hilos acceden a un recurso compartido sin sincronización adecuada, causando resultados inconsistentes.

**Ejemplo de Condición de Carrera**:

```java
class Cuenta {
    private int saldo = 0;

    public void depositar(int cantidad) {
        saldo += cantidad; // Acceso no sincronizado
    }

    public int getSaldo() {
        return saldo;
    }
}

public class Main {
    public static void main(String[] args) {
        Cuenta cuenta = new Cuenta();

        // Tarea de depósito concurrente
        Runnable tarea = () -> {
            for (int i = 0; i < 1000; i++) {
                cuenta.depositar(1);
            }
        };

        Thread hilo1 = new Thread(tarea);
        Thread hilo2 = new Thread(tarea);

        hilo1.start();
        hilo2.start();

        try {
            hilo1.join();
            hilo2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Saldo final: " + cuenta.getSaldo());
    }
}
```

**Explicación**:
- Los hilos `hilo1` y `hilo2` están ejecutando `depositar` al mismo tiempo, accediendo a `saldo` sin sincronización, lo que da un saldo inconsistente.

**Solución**:
- Utiliza la palabra clave `synchronized` en el método `depositar` para que solo un hilo pueda modificar `saldo` a la vez.

### **Livelock**

Un *livelock* es similar a un deadlock, pero en lugar de quedar bloqueados, los hilos están en un bucle de ejecución tratando de resolver el conflicto, sin lograrlo. Esto puede suceder cuando los hilos hacen cambios en sus estados sin llegar a avanzar.

**Ejemplo de Livelock** (pseudocódigo, ya que es más complicado en Java):

```java
// Supongamos que dos hilos intentan acceder a un recurso compartido y continuamente
// cambian de estado sin lograr adquirirlo, ajustando su comportamiento y
// entrando en un bucle infinito sin avanzar.
```

**Solución**:
- Limita el número de intentos que un hilo puede hacer o utiliza `tryLock()` de la clase `ReentrantLock`, que permite a un hilo intentar adquirir un bloqueo por un tiempo específico antes de rendirse.

### **Starvation (Inanición)**

La inanición ocurre cuando un hilo no puede obtener acceso a los recursos que necesita para progresar, ya que otros hilos monopolizan los recursos. Esto es común en sistemas con alta prioridad de hilos, donde los hilos de baja prioridad no obtienen tiempo suficiente de CPU.

**Solución**:
- Evita el uso excesivo de prioridades de hilos.
- Usa mecanismos de espera como `wait()` y `notify()` o semáforos (`Semaphore`) para equilibrar el acceso a los recursos.

### **Uso Inapropiado de `wait()` y `notify()`**

El uso incorrecto de `wait()` y `notify()` puede provocar problemas de concurrencia, como que los hilos se queden esperando indefinidamente o que se pierdan notificaciones. Estos métodos deben usarse dentro de un bloque `synchronized` y se deben llamar desde el mismo objeto monitor.

**Ejemplo Incorrecto**:

```java
class EsperaNotificacion {
    public void esperar() throws InterruptedException {
        wait(); // Esto lanzará una excepción IllegalMonitorStateException
    }

    public synchronized void notificar() {
        notify();
    }
}
```

**Solución**:
- Usa `wait()` y `notify()` dentro de un bloque `synchronized` del mismo objeto, para que el hilo tenga el bloqueo del monitor necesario.

**Ejemplo Correcto**:

```java
class EsperaNotificacion {
    public synchronized void esperar() throws InterruptedException {
        wait();
    }

    public synchronized void notificar() {
        notify();
    }
}
```

### **Bloqueo Demasiado Amplio (Over-Synchronization)**

A veces, sincronizar todo un método o múltiples bloques innecesariamente puede reducir el rendimiento de la aplicación, ya que los hilos quedan bloqueados sin necesidad. Esto se llama *over-synchronization* y puede provocar cuellos de botella.

**Solución**:
- Sincroniza solo el código crítico que necesita protección. Usa bloques `synchronized` en lugar de sincronizar métodos enteros cuando sea posible.

### **Interrupción de Hilos (`InterruptedException`)**

El uso incorrecto de la interrupción de hilos puede llevar a problemas. La interrupción de un hilo no detiene su ejecución automáticamente; debe manejarse con cuidado y responder a la interrupción de forma adecuada.

**Ejemplo de Interrupción**:

```java
public class InterruptionExample implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Simula una tarea larga
                Thread.sleep(1000);
                System.out.println("Hilo trabajando");
            } catch (InterruptedException e) {
                System.out.println("Hilo interrumpido");
                Thread.currentThread().interrupt(); // Vuelve a establecer el estado de interrupción
            }
        }
    }

    public static void main(String[] args) {
        Thread hilo = new Thread(new InterruptionExample());
        hilo.start();

        try { Thread.sleep(3000); } catch (InterruptedException e) { e.printStackTrace(); }

        hilo.interrupt(); // Interrumpimos el hilo
    }
}
```

**Solución**:
- Maneja la interrupción adecuadamente. Al capturar `InterruptedException`, restablece el estado de interrupción si el hilo debe ser interrumpido nuevamente.

---
