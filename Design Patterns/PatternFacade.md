## TEORÍA

El patrón de diseño *Facade* es un patrón estructural que se utiliza para proporcionar una interfaz simplificada a un conjunto de interfaces en un subsistema. Este patrón es especialmente útil cuando un sistema es complejo y tiene muchas interdependencias. Al introducir una fachada, puedes ocultar la complejidad del sistema y proporcionar una interfaz fácil de usar para los clientes.

### ¿Cuándo utilizar el patrón Facade?
- Cuando quieres simplificar el acceso a un sistema complejo.
- Cuando deseas desacoplar el código del cliente de las clases del subsistema, facilitando su mantenimiento y modificación.
- Cuando quieres organizar mejor el código, dividiendo la funcionalidad en subsistemas y exponiéndolos a través de una fachada.

### Ejemplo de Código en Java

Imaginemos que tienes un sistema de cine para reservar entradas. Este sistema tiene varios subsistemas, como los que gestionan la disponibilidad de entradas, el pago y las notificaciones. Implementaremos el patrón Facade para simplificar la interacción del cliente con estos subsistemas.

#### Paso 1: Crear las clases del subsistema

```java
class TicketAvailability {
    public boolean checkAvailability(int movieId) {
        System.out.println("Checking ticket availability for movie ID: " + movieId);
        // Suponiendo que hay entradas disponibles
        return true;
    }
}

class PaymentProcessor {
    public void processPayment(String paymentType, double amount) {
        System.out.println("Processing " + paymentType + " payment of $" + amount);
        // Lógica de procesamiento de pago
    }
}

class NotificationService {
    public void sendConfirmation(String message) {
        System.out.println("Sending confirmation: " + message);
    }
}
```

#### Paso 2: Crear la clase `Facade`

La clase `MovieBookingFacade` actúa como la fachada para el sistema de reserva de entradas. Utiliza internamente las clases del subsistema para ofrecer una interfaz sencilla al cliente.

```java
class MovieBookingFacade {
    private TicketAvailability ticketAvailability;
    private PaymentProcessor paymentProcessor;
    private NotificationService notificationService;

    public MovieBookingFacade() {
        this.ticketAvailability = new TicketAvailability();
        this.paymentProcessor = new PaymentProcessor();
        this.notificationService = new NotificationService();
    }

    public void bookTicket(int movieId, String paymentType, double amount) {
        if (ticketAvailability.checkAvailability(movieId)) {
            paymentProcessor.processPayment(paymentType, amount);
            notificationService.sendConfirmation("Booking confirmed for movie ID: " + movieId);
            System.out.println("Ticket booked successfully!");
        } else {
            System.out.println("Sorry, tickets are not available for movie ID: " + movieId);
        }
    }
}
```

#### Paso 3: Uso de la clase `Facade` en el cliente

El cliente interactúa únicamente con la clase `MovieBookingFacade`, sin necesidad de conocer la complejidad interna del sistema de reserva.

```java
public class Main {
    public static void main(String[] args) {
        MovieBookingFacade bookingFacade = new MovieBookingFacade();
        bookingFacade.bookTicket(1, "Credit Card", 15.99);
    }
}
```

### Explicación del Código
1. El cliente crea una instancia de `MovieBookingFacade` y llama al método `bookTicket`.
2. El método `bookTicket` en la clase `MovieBookingFacade` se encarga de:
   - Verificar la disponibilidad de entradas mediante `TicketAvailability`.
   - Procesar el pago mediante `PaymentProcessor`.
   - Enviar una confirmación mediante `NotificationService`.
3. La fachada oculta la complejidad del subsistema y proporciona una única interfaz de punto de acceso para el cliente.

Con el patrón Facade, el cliente puede realizar una reserva sin preocuparse por cómo funcionan internamente cada uno de los subsistemas.


## EJERCICIO: Sistema de Gestión de Pedidos
Crearemos un sistema de **gestión de pedidos** en una tienda en línea. Este sistema tendrá varios subsistemas para procesar el pedido, realizar el pago y enviar la notificación, y utilizarás el patrón Facade para simplificar la interacción con estos subsistemas.

#### Contexto
Una tienda en línea desea simplificar el proceso de gestión de pedidos, que incluye las siguientes tareas:
1. Verificar la disponibilidad del producto.
2. Procesar el pago del cliente.
3. Organizar el envío del pedido.
4. Notificar al cliente de que su pedido se ha completado.

#### Objetivo
Implementar una clase `OrderFacade` que integre estos subsistemas y proporcione una interfaz simple para realizar pedidos.

#### Detalles del Ejercicio

1. **Clases del subsistema**:
   - **Inventory**: Verifica la disponibilidad del producto.
   - **Payment**: Procesa el pago del cliente.
   - **Shipping**: Organiza el envío del pedido.
   - **Notification**: Envía una confirmación al cliente.

2. **Clase Facade**:
   - **OrderFacade**: Proporcionará el método `placeOrder` para que el cliente pueda hacer un pedido sin tener que interactuar directamente con los subsistemas.

3. **Cliente**:
   - Implementa un programa en `Main` que interactúe solo con la clase `OrderFacade` para hacer un pedido.

#### Estructura

**Paso 1:** Implementar las clases del subsistema.

**Paso 2:** Implementar la clase `OrderFacade`.

**Paso 3:** Crear la clase `Main` para que el cliente pueda interactuar con el sistema a través de la clase `OrderFacade`.

### Requisitos del Ejercicio

1. **Implementar todas las clases del subsistema (Inventory, Payment, Shipping y Notification)**.
2. **Implementar la clase `OrderFacade`** para que interactúe con cada uno de los subsistemas en el método `placeOrder`.
3. **Probar el sistema** utilizando la clase `Main`, que debe realizar un pedido usando solo `OrderFacade`.

### Preguntas de reflexión

Al finalizar, responde las siguientes preguntas para reflexionar sobre el ejercicio:

1. ¿Por qué el cliente no necesita conocer los detalles de cada subsistema?
2. ¿Cómo simplifica el patrón Facade el código del cliente?
3. ¿Qué ventajas tendría modificar uno de los subsistemas sin afectar el código del cliente?