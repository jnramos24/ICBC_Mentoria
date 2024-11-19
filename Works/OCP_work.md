¡Por supuesto! Aquí tienes un ejercicio práctico para aplicar el principio OCP en Java:

### Ejercicio

Imagina que tienes una aplicación que gestiona diferentes tipos de notificaciones. Actualmente, solo puedes enviar notificaciones por **correo electrónico**. Tu objetivo es mejorar la aplicación para permitir el envío de notificaciones mediante otros medios, como **SMS** o **notificaciones push**, sin modificar el código de la clase que administra las notificaciones.

### Requerimientos

1. Crear una interfaz `Notification` con un método `send`.
2. Implementar una clase `EmailNotification` que represente el envío de notificaciones por correo electrónico.
3. Implementar una clase `NotificationSender` que se encargue de enviar las notificaciones usando cualquier implementación de `Notification`.
4. Agregar una nueva clase `SMSNotification` sin modificar el código de `NotificationSender` para que esta clase pueda gestionar las notificaciones SMS además de las de correo electrónico.

### Paso 1: Define la Interfaz `Notification`

Define una interfaz `Notification` con el método `send`. Este método no debería recibir ningún parámetro.

### Paso 2: Implementa `EmailNotification`

Crea una clase `EmailNotification` que implemente la interfaz `Notification` y defina el método `send` para simular el envío de un correo electrónico.

### Paso 3: Crea la Clase `NotificationSender`

Implementa una clase `NotificationSender` que dependa de la interfaz `Notification` y tenga un método `sendNotification`. Esta clase debería aceptar cualquier implementación de `Notification` para cumplir con el principio OCP.

### Paso 4: Agrega una Nueva Clase `SMSNotification`

Implementa una nueva clase `SMSNotification` sin modificar `NotificationSender`. `SMSNotification` debería también implementar la interfaz `Notification` y su método `send` para simular el envío de un SMS.

### Paso 5: Prueba el Código

Crea una clase `Main` para probar que `NotificationSender` funciona con ambas implementaciones de `Notification`.
```

### Salida Esperada

Al ejecutar el código, deberías ver algo similar a esto:

```
Sending Email Notification...
Sending SMS Notification...
```

