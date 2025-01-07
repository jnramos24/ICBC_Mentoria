El patrón de diseño **Bridge** (puente) es un patrón estructural que se utiliza para desacoplar una abstracción de su implementación para que ambas puedan variar de manera independiente. Es útil cuando tienes clases que podrían ser extendidas en múltiples dimensiones (por ejemplo, por jerarquías diferentes de abstracción e implementación).

### Componentes principales del patrón Bridge:
1. **Abstracción**: Define una interfaz abstracta que utiliza una implementación concreta.
2. **Implementación**: Define la interfaz para las implementaciones concretas.
3. **Refinamiento de la abstracción**: Extiende la abstracción y usa la implementación a través de su interfaz.
4. **Implementación concreta**: Implementa la interfaz de la implementación.

### Ventajas:
- Permite variar la implementación y la abstracción de manera independiente.
- Facilita la extensión del código.
- Reduce la dependencia entre componentes.

El patrón de diseño **Bridge** se utiliza principalmente para resolver problemas de **multiplicación de clases** y **alto acoplamiento** en escenarios donde tienes una abstracción (una clase base o interfaz) y varias implementaciones que podrían cambiar independientemente.

### **Problema que soluciona**
Imagina que estás diseñando un sistema para manejar formas geométricas y colores. Por cada combinación de forma y color, podrías terminar creando una nueva clase. Por ejemplo:

- Si tienes las formas **Círculo** y **Cuadrado**, y los colores **Rojo** y **Azul**, necesitarías clases como:
  - `CírculoRojo`
  - `CírculoAzul`
  - `CuadradoRojo`
  - `CuadradoAzul`

Ahora, si agregas una nueva forma (Triángulo) o un nuevo color (Verde), necesitarías agregar varias clases más. Esto lleva a un **explosivo crecimiento de clases** y a un sistema difícil de mantener.

El patrón **Bridge** soluciona este problema al separar la jerarquía de formas (abstracción) de la jerarquía de colores (implementación). Así, las formas y colores pueden evolucionar de manera independiente, sin que haya una dependencia rígida entre ambas.

---

### **Ejemplo paso a paso con explicación**

1. **Sin usar el patrón Bridge**  
   Supongamos que queremos manejar formas y colores directamente. Esto podría resultar en algo como:

```java
// Una clase para cada combinación de forma y color
class CírculoRojo { /* Código específico para un círculo rojo */ }
class CírculoAzul { /* Código específico para un círculo azul */ }
class CuadradoRojo { /* Código específico para un cuadrado rojo */ }
class CuadradoAzul { /* Código específico para un cuadrado azul */ }
// ¡Esto escala rápidamente cuando agregas nuevas formas o colores!
```

Este enfoque **no es escalable** y **es difícil de mantener**. Cada cambio en una forma o color requeriría modificar o agregar nuevas clases.

---

2. **Usando el patrón Bridge**

Ahora, separaremos las responsabilidades:

- **Abstracción (Formas)**: Una jerarquía que representa las formas (Círculo, Cuadrado, etc.).
- **Implementación (Colores)**: Una jerarquía que representa los colores (Rojo, Azul, etc.).
- La abstracción (`Shape`) tendrá una referencia a la implementación (`Color`).

**Código mejorado usando Bridge:**

```java
// Interfaz para los colores (Implementación)
interface Color {
    void applyColor();
}

// Clases concretas que implementan colores específicos
class RedColor implements Color {
    
    @Override
    public void applyColor() {
        System.out.println("Aplicando color rojo");
    }
}

class BlueColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("Aplicando color azul");
    }
}

// Clase abstracta para formas (Abstracción)
abstract class Shape {
    protected Color color;

    // Constructor que conecta la abstracción con la implementación
    public Shape(Color color) {
        this.color = color;
    }

    // Método abstracto para ser implementado por las formas específicas
    abstract void draw();
}

// Clases concretas que extienden la abstracción para formas específicas
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Dibujando un círculo con color: ");
        color.applyColor(); // Llamada a la implementación
    }
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    @Override
    void draw() {
        System.out.print("Dibujando un cuadrado con color: ");
        color.applyColor(); // Llamada a la implementación
    }
}

// Clase cliente que utiliza el patrón Bridge
public class BridgePatternDemo {
    public static void main(String[] args) {
        // Crea formas con diferentes colores
        Shape redCircle = new Circle(new RedColor());
        Shape blueSquare = new Square(new BlueColor());

        // Dibuja las formas
        redCircle.draw();  // Salida: Dibujando un círculo con color: Aplicando color rojo
        blueSquare.draw(); // Salida: Dibujando un cuadrado con color: Aplicando color azul
    }
}
```

---

### **Ventajas del patrón Bridge**

1. **Escalabilidad**: Si necesitas agregar un nuevo color (por ejemplo, Verde), solo creas una nueva clase que implemente `Color`. No necesitas modificar las formas.
2. **Mantenimiento simplificado**: Las jerarquías de formas y colores están desacopladas. Los cambios en una jerarquía no afectan a la otra.
3. **Evita la explosión de clases**: En lugar de crear una clase para cada combinación de forma y color, se combinan dinámicamente.
4. **Flexibilidad**: Puedes cambiar el color de una forma sin cambiar el diseño general.

---

### **Cuando usar el patrón Bridge**

1. Cuando necesitas dividir una clase en dos jerarquías independientes que pueden variar de manera separada.
2. Cuando las combinaciones posibles de abstracciones e implementaciones son muchas y no quieres crear una clase para cada combinación.
3. Cuando quieres evitar un fuerte acoplamiento entre la abstracción y la implementación.

El patrón **Bridge** es ideal para sistemas complejos que requieren extensibilidad y modularidad.

## **Ejercicio: Sistema de Notificaciones Multicanal**

### **Contexto del Problema**
Estás desarrollando un sistema de notificaciones para una empresa. El sistema debe poder enviar notificaciones a través de diferentes **canales** (por ejemplo, correo electrónico, SMS, o notificaciones push) y para diferentes **tipos de mensajes** (por ejemplo, notificaciones de alerta, recordatorios, promociones).

Cada canal tiene una forma diferente de entregar el mensaje, y cada tipo de mensaje tiene un formato específico. Necesitas diseñar un sistema que sea flexible, extensible y que evite la explosión de clases que resultaría de manejar cada combinación de canal y tipo de mensaje.

---

### **Requisitos**
1. **Canales disponibles**:
   - Correo electrónico.
   - SMS.
   - Push (notificación móvil).

2. **Tipos de mensajes**:
   - Alertas críticas.
   - Recordatorios.
   - Promociones.

3. El sistema debe ser extensible:
   - Es fácil agregar nuevos canales o tipos de mensajes sin modificar el código existente.

4. Utilizar el patrón de diseño **Bridge** para separar los **canales** (implementación) de los **tipos de mensajes** (abstracción).

---

### **Objetivo**
1. Diseñar las clases necesarias para implementar el sistema utilizando el patrón **Bridge**.
2. Probar el sistema creando notificaciones para cada combinación de canales y tipos de mensajes.

---

### **Pistas**
1. **Define una interfaz para los canales de notificación**:
   - Cada canal debe implementar un método que envíe el mensaje con un contenido dado.

2. **Crea una clase abstracta para los tipos de mensajes**:
   - Esta clase debe tener una referencia a un canal (la "implementación").

3. **Implementa clases concretas para los canales**:
   - Por ejemplo, `EmailChannel`, `SmsChannel`, y `PushChannel`.

4. **Implementa clases concretas para los tipos de mensajes**:
   - Por ejemplo, `CriticalAlertMessage`, `ReminderMessage`, y `PromotionMessage`.

5. En el programa principal, combina dinámicamente los tipos de mensajes con los canales.

---

### **Ejemplo de diseño esperado**
1. **Canal (Implementación)**:
   - `NotificationChannel` (interfaz).
   - `EmailChannel`, `SmsChannel`, `PushChannel` (clases concretas).

2. **Mensaje (Abstracción)**:
   - `Message` (clase abstracta).
   - `CriticalAlertMessage`, `ReminderMessage`, `PromotionMessage` (clases concretas).

---

### **Código inicial**
A continuación, te proporcionamos una base para empezar:

```java
// Interfaz para los canales de notificación
interface NotificationChannel {
    void send(String content);
}

// Clase abstracta para los tipos de mensajes
abstract class Message {
    protected NotificationChannel channel;

    public Message(NotificationChannel channel) {
        this.channel = channel;
    }

    abstract void sendMessage(String msg);
}
```

---

### **Tareas**
1. Completa las clases concretas para los canales de notificación:
   - Por ejemplo, en `EmailChannel`, imprime "Enviando correo: [contenido]".
   - En `SmsChannel`, imprime "Enviando SMS: [contenido]".
   - En `PushChannel`, imprime "Enviando notificación push: [contenido]".

2. Completa las clases concretas para los tipos de mensajes:
   - Por ejemplo, en `CriticalAlertMessage`, define el contenido del mensaje como "ALERTA CRÍTICA: [detalles]".
   - En `ReminderMessage`, define el contenido como "Recordatorio: [detalles]".
   - En `PromotionMessage`, define el contenido como "Promoción: [detalles]".

3. En el programa principal:
   - Combina canales y tipos de mensajes.
   - Envía mensajes utilizando diferentes combinaciones.

---

### **Entrega esperada**
Un código funcional que permita algo como:

```java
public class NotificationSystemDemo {
    public static void main(String[] args) {
        // Crear un canal de correo electrónico
        NotificationChannel emailChannel = new EmailChannel();

        // Crear un mensaje de alerta crítica y enviarlo por correo electrónico
        Message criticalAlert = new CriticalAlertMessage(emailChannel);
        criticalAlert.sendMessage("¡Problema en el sistema!");

        // Crear un mensaje de promoción y enviarlo por SMS
        NotificationChannel smsChannel = new SmsChannel();
        Message promotion = new PromotionMessage(smsChannel);
        promotion.sendMessage("20% de descuento en tu próxima compra.");
    }
}
```

**Salida esperada**:
```
Enviando correo: ALERTA CRÍTICA: ¡Problema en el sistema!
Enviando SMS: Promoción: 20% de descuento en tu próxima compra.
```

---

### **Extensión adicional**
Para un desafío adicional:
1. Agrega un nuevo canal, como "Notificación en Slack".
2. Agrega un nuevo tipo de mensaje, como "Encuesta de satisfacción".

Este ejercicio te permitirá comprender cómo el patrón **Bridge** desacopla la jerarquía de abstracción (tipos de mensajes) de la implementación (canales), haciendo que el sistema sea fácil de escalar y mantener.