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