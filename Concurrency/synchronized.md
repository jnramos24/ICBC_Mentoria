La palabra clave `synchronized` en Java se utiliza para controlar el acceso a secciones de código que pueden ser accedidas por múltiples hilos al mismo tiempo. Ayuda a evitar problemas de concurrencia, como las condiciones de carrera, asegurando que solo un hilo puede ejecutar el código sincronizado en un momento dado.

### ¿Cómo Funciona `synchronized`?
Cuando un método o un bloque de código está marcado como `synchronized`, Java utiliza un "candado" o "monitor" para que solo un hilo pueda acceder a esa sección mientras esté en uso. Una vez que el hilo termina, el candado se libera y otro hilo puede acceder.

### Ejemplo 1: Sincronización de un Método

En este ejemplo, sincronizamos un método para que solo un hilo pueda modificar el saldo de una cuenta bancaria a la vez.

```java
class CuentaBancaria {
    private int saldo = 100;

    public synchronized void retirarDinero(int cantidad) {
        if (saldo >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " está retirando " + cantidad);
            saldo -= cantidad;
            System.out.println("Saldo restante: " + saldo);
        } else {
            System.out.println("Saldo insuficiente para " + Thread.currentThread().getName());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        CuentaBancaria cuenta = new CuentaBancaria();

        Runnable tareaRetiro = () -> cuenta.retirarDinero(50);

        Thread hilo1 = new Thread(tareaRetiro, "Cliente 1");
        Thread hilo2 = new Thread(tareaRetiro, "Cliente 2");

        hilo1.start();
        hilo2.start();
    }
}
```

**Explicación**:
- Aquí, el método `retirarDinero` está marcado como `synchronized`, lo que garantiza que solo un hilo puede ejecutarlo a la vez.
- Esto evita que ambos hilos intenten retirar dinero simultáneamente y causen problemas de saldo inconsistente.

### Ejemplo 2: Sincronización de un Bloque de Código

Si no necesitas sincronizar un método completo, puedes sincronizar solo una parte específica usando un bloque `synchronized` con un objeto que actúe como candado (en este caso, `this`).

```java
class Contador {
    private int conteo = 0;

    public void incrementar() {
        synchronized(this) { // Bloque sincronizado
            conteo++;
            System.out.println("Conteo incrementado a: " + conteo + " por " + Thread.currentThread().getName());
        }
    }

    public int getConteo() {
        return conteo;
    }
}

public class Main {
    public static void main(String[] args) {
        Contador contador = new Contador();

        Runnable tareaIncremento = contador::incrementar;

        Thread hilo1 = new Thread(tareaIncremento, "Hilo 1");
        Thread hilo2 = new Thread(tareaIncremento, "Hilo 2");
        Thread hilo3 = new Thread(tareaIncremento, "Hilo 3");

        hilo1.start();
        hilo2.start();
        hilo3.start();
    }
}
```

**Explicación**:
- Aquí, solo el bloque que incrementa `conteo` está sincronizado, mientras que el resto de la clase no lo está.
- Al usar `synchronized(this)`, estamos usando el objeto `Contador` como el candado para que solo un hilo a la vez pueda acceder a ese bloque de código.

### Ejemplo 3: Sincronización de Acceso a un Recurso Compartido

Imagina que tienes un recurso compartido, como un inventario. Los hilos intentarán restar o sumar unidades al inventario, y `synchronized` ayuda a evitar inconsistencias.

```java
class Inventario {
    private int stock = 10;

    public synchronized void venderProducto(int cantidad) {
        if (stock >= cantidad) {
            System.out.println(Thread.currentThread().getName() + " vendió " + cantidad + " productos.");
            stock -= cantidad;
            System.out.println("Stock restante: " + stock);
        } else {
            System.out.println("Stock insuficiente para " + Thread.currentThread().getName());
        }
    }

    public synchronized void reponerProducto(int cantidad) {
        System.out.println(Thread.currentThread().getName() + " repuso " + cantidad + " productos.");
        stock += cantidad;
        System.out.println("Stock actual: " + stock);
    }
}

public class Main {
    public static void main(String[] args) {
        Inventario inventario = new Inventario();

        // Tareas de venta y reposición
        Runnable vender = () -> inventario.venderProducto(5);
        Runnable reponer = () -> inventario.reponerProducto(10);

        Thread vendedor1 = new Thread(vender, "Vendedor 1");
        Thread vendedor2 = new Thread(vender, "Vendedor 2");
        Thread reponedor = new Thread(reponer, "Reponedor");

        vendedor1.start();
        vendedor2.start();
        reponedor.start();
    }
}
```

**Explicación**:
- En este ejemplo, los métodos `venderProducto` y `reponerProducto` están sincronizados. Esto asegura que solo un hilo puede realizar una venta o reposición a la vez.
- Esto evita que el stock se vuelva inconsistente si dos hilos intentan modificarlo al mismo tiempo.

### Ejemplo 4: Sincronización en un Objeto Independiente

Puedes usar cualquier objeto como candado para sincronizar un bloque de código específico. Aquí, usamos un objeto independiente como monitor.

```java
class Banco {
    private int balance = 100;
    private final Object lock = new Object();

    public void depositar(int cantidad) {
        synchronized (lock) { // Usamos `lock` como el objeto sincronizado
            balance += cantidad;
            System.out.println("Depositado " + cantidad + ", Balance actual: " + balance);
        }
    }

    public void retirar(int cantidad) {
        synchronized (lock) {
            if (balance >= cantidad) {
                balance -= cantidad;
                System.out.println("Retirado " + cantidad + ", Balance actual: " + balance);
            } else {
                System.out.println("Fondos insuficientes");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco();

        // Crear tareas para depositar y retirar
        Runnable tareaDeposito = () -> banco.depositar(50);
        Runnable tareaRetiro = () -> banco.retirar(30);

        Thread hiloDeposito = new Thread(tareaDeposito, "Deposito");
        Thread hiloRetiro = new Thread(tareaRetiro, "Retiro");

        hiloDeposito.start();
        hiloRetiro.start();
    }
}
```

**Explicación**:
- Aquí, usamos el objeto `lock` para sincronizar las operaciones de depósito y retiro, asegurando que solo un hilo pueda acceder a ese bloque a la vez.
- Esto es útil si solo deseas sincronizar un bloque específico sin bloquear todo el objeto.

Estos ejemplos cubren varios usos de `synchronized`, lo cual es esencial para evitar conflictos cuando varios hilos acceden a recursos compartidos en Java.