Aquí tienes algunos ejemplos prácticos para manejar la concurrencia en Java usando `Thread` e `Runnable`.

### Ejemplo 1: Implementación Básica de `Runnable`

Este ejemplo muestra cómo implementar la interfaz `Runnable` y ejecutar un hilo que imprime un mensaje:

```java
class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hilo ejecutándose: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable()); // Creamos un hilo con la tarea `MyRunnable`
        thread.start(); // Iniciamos el hilo
    }
}
```

### Ejemplo 2: Crear Múltiples Hilos con `Runnable`

En este ejemplo, creamos múltiples hilos que ejecutan la misma tarea `Runnable`, mostrando que los hilos funcionan de manera independiente.

```java
class Task implements Runnable {
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        System.out.println(taskName + " ejecutándose en: " + Thread.currentThread().getName());
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Task("Tarea 1"));
        Thread thread2 = new Thread(new Task("Tarea 2"));
        Thread thread3 = new Thread(new Task("Tarea 3"));

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
```

### Ejemplo 3: Usando `Runnable` con un Bucle

Este ejemplo muestra un `Runnable` que ejecuta un bucle de 5 iteraciones. Cada hilo tendrá su propio bucle y ejecutará las iteraciones de forma independiente.

```java
class LoopTask implements Runnable {
    private final String taskName;

    public LoopTask(String taskName) {
        this.taskName = taskName;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(taskName + " - Iteración " + i + " en: " + Thread.currentThread().getName());
            try {
                Thread.sleep(500); // Simula un retardo en la ejecución
            } catch (InterruptedException e) {
                System.err.println("Hilo interrumpido: " + e.getMessage());
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new LoopTask("Bucle A"));
        Thread thread2 = new Thread(new LoopTask("Bucle B"));

        thread1.start();
        thread2.start();
    }
}
```

### Ejemplo 4: Crear Hilos Anónimos con `Runnable`

Si solo necesitas ejecutar una tarea rápida y no quieres definir una clase aparte, puedes crear un `Runnable` anónimo:

```java
public class Main {
    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hilo anónimo ejecutándose en: " + Thread.currentThread().getName());
            }
        });
        thread.start();
    }
}
```

### Ejemplo 5: `Runnable` Usando Expresiones Lambda

Desde Java 8, puedes usar expresiones lambda para simplificar el código cuando implementas `Runnable`. Esto es útil para tareas pequeñas.

```java
public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Hilo con lambda ejecutándose en: " + Thread.currentThread().getName());
        };

        Thread thread = new Thread(task);
        thread.start();
    }
}
```

### Ejemplo 6: Ejecución en Paralelo de Tareas con `Runnable`

En este ejemplo, se ejecutan múltiples tareas `Runnable` en paralelo para ver cómo funcionan de forma concurrente:

```java
public class Main {
    public static void main(String[] args) {
        Runnable task1 = () -> System.out.println("Tarea 1 en: " + Thread.currentThread().getName());
        Runnable task2 = () -> System.out.println("Tarea 2 en: " + Thread.currentThread().getName());
        Runnable task3 = () -> System.out.println("Tarea 3 en: " + Thread.currentThread().getName());

        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        Thread thread3 = new Thread(task3);

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
```

Estos ejemplos te permitirán experimentar y entender cómo funcionan los hilos y `Runnable` en Java. Cada ejemplo aborda un caso común en el manejo de hilos para ejecutar tareas concurrentemente.