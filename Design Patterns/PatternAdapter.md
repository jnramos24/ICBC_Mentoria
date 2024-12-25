## TEORÍA

El patrón de diseño **Adapter** se utiliza para hacer compatible una clase con una interfaz que de otra manera sería incompatible. Este patrón actúa como un intermediario entre dos clases, permitiendo que trabajen juntas sin necesidad de modificar sus estructuras.

### Ejemplo práctico del patrón Adapter

Imaginemos que tenemos una aplicación que maneja dos tipos de audio: `MP3` y `MP4`. Queremos que ambos formatos funcionen con un reproductor de audio, pero nuestra aplicación solo acepta el tipo `MP3`. Aquí es donde el patrón **Adapter** nos ayuda, permitiéndonos reproducir un `MP4` usando un adaptador.

### Paso 1: Definir la interfaz objetivo (`MediaPlayer`)

```java
// Esta es la interfaz que nuestra aplicación usa
interface MediaPlayer {
    void play(String audioType, String fileName);
}
```

### Paso 2: Crear clases concretas que implementan la interfaz (`MP3Player`)

```java
// Clase concreta que reproduce archivos MP3
class MP3Player implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Reproduciendo archivo MP3: " + fileName);
        } else {
            System.out.println("Formato no soportado");
        }
    }
}
```

### Paso 3: Crear la clase incompatible (`MP4Player`)

Esta clase no implementa la interfaz `MediaPlayer`, por lo tanto, necesitamos un adaptador.

```java
// Clase concreta que reproduce archivos MP4
class MP4Player {
    public void playMP4(String fileName) {
        System.out.println("Reproduciendo archivo MP4: " + fileName);
    }
}
```

### Paso 4: Crear el adaptador (`MediaAdapter`)

El adaptador implementará la interfaz `MediaPlayer` y, a su vez, utilizará una instancia de `MP4Player` para reproducir archivos MP4.

```java
// Adaptador que permite usar MP4Player como si fuera un MediaPlayer
class MediaAdapter implements MediaPlayer {
    MP4Player mp4Player;

    public MediaAdapter(String audioType) {
        if(audioType.equalsIgnoreCase("mp4")) {
            mp4Player = new MP4Player();
        }
    }

    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp4")) {
            mp4Player.playMP4(fileName);
        }
    }
}
```

### Paso 5: Crear el reproductor de audio con adaptador (`AudioPlayer`)

`AudioPlayer` utilizará `MediaAdapter` para reproducir archivos MP4 cuando sea necesario.

```java
// Clase principal que usará el adaptador para soportar otros formatos
class AudioPlayer implements MediaPlayer {
    MediaAdapter mediaAdapter;

    @Override
    public void play(String audioType, String fileName) {
        // Reproduce MP3 directamente
        if(audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Reproduciendo archivo MP3: " + fileName);
        }
        // Usa el adaptador para reproducir MP4
        else if(audioType.equalsIgnoreCase("mp4")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        else {
            System.out.println("Formato no soportado: " + audioType);
        }
    }
}
```

### Paso 6: Probar la implementación

```java
public class AdapterPatternDemo {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.play("mp3", "cancion.mp3");
        audioPlayer.play("mp4", "video.mp4");
        audioPlayer.play("vlc", "pelicula.vlc"); // formato no soportado
    }
}
```

### Resultado esperado

Al ejecutar el código, obtendrás una salida como la siguiente:

```plaintext
Reproduciendo archivo MP3: cancion.mp3
Reproduciendo archivo MP4: video.mp4
Formato no soportado: vlc
```

### Explicación del flujo

1. `AudioPlayer` intenta reproducir un archivo.
2. Si el tipo es `mp3`, lo reproduce directamente.
3. Si el tipo es `mp4`, usa `MediaAdapter` que a su vez llama a `MP4Player` para reproducir el archivo.
4. Si el formato no es compatible, muestra un mensaje de error.

El **Adapter** permite que `MP4Player` funcione con la interfaz `MediaPlayer` sin modificar ninguna de las clases originales, manteniendo el principio de **abierto/cerrado** del diseño de software.


## EJERCICIO: Sistema de Procesamiento de Pagos

Este ejercicio simulará un sistema de pagos que necesita integrar dos formas de procesadores de pagos distintos. 
Supongamos que tienes un sistema de comercio electrónico que solo acepta pagos a través de un procesador llamado `OldPaymentSystem`. Ahora quieres integrar un nuevo procesador llamado `NewPaymentProcessor` sin cambiar demasiado tu sistema actual. Para lograr esto, usarás el patrón **Adapter** y manejarás las posibles excepciones que puedan surgir durante el proceso de pago.

#### Requisitos

1. Crea una interfaz `PaymentProcessor` que defina el método `processPayment(double amount)`.
2. Implementa una clase `OldPaymentSystem` que implemente `PaymentProcessor` y simule el procesamiento de pagos con impresión en consola.
3. Crea una clase `NewPaymentProcessor` que tenga un método `makePayment(double amount)` y que pueda lanzar una excepción `PaymentFailedException`.
4. Implementa una clase `PaymentFailedException` que extienda `Exception` y represente errores de pago.
5. Implementa una clase `PaymentAdapter` que actúe como un adaptador entre `NewPaymentProcessor` y `PaymentProcessor`.
6. Escribe código en el `main` que demuestre el uso del adaptador y maneje las excepciones que puedan surgir.