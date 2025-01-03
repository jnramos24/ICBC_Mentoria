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