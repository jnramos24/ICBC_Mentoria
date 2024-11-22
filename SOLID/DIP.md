El **principio de inversión de dependencia (Dependency Inversion Principle, DIP)** es uno de los principios SOLID y establece lo siguiente:

1. **Los módulos de alto nivel no deben depender de los módulos de bajo nivel. Ambos deben depender de abstracciones.**
2. **Las abstracciones no deben depender de los detalles. Los detalles deben depender de abstracciones.**

El objetivo del DIP es reducir la dependencia entre diferentes partes del sistema para hacerlo más flexible, reutilizable y fácil de mantener. Esto se logra mediante la introducción de interfaces o abstracciones que separan los detalles de implementación de las capas de alto nivel.

### Explicación detallada
- **Módulo de alto nivel:** Es la parte del sistema que realiza operaciones complejas o de nivel empresarial.
- **Módulo de bajo nivel:** Son los componentes que realizan tareas específicas, como el acceso a la base de datos o la implementación de un algoritmo concreto.
- **Abstracción:** Una interfaz o clase abstracta que actúa como un contrato entre los módulos de alto nivel y bajo nivel.
---

Vamos a probar con un ejemplo relacionado con un sistema de **logística y transporte**, donde el sistema gestiona el envío de mercancías utilizando diferentes tipos de transportes (camión, avión, barco).

### Sistema de logística y transporte
El negocio necesita enviar mercancías utilizando diferentes métodos de transporte según las necesidades del cliente o la disponibilidad (por camión, avión o barco). Implementaremos el sistema de forma que el módulo principal no dependa directamente de las implementaciones específicas de transporte.

#### Código aplicando DIP:
```java
// Abstraction (Interface)
interface TransportService {
    void deliver(String cargo);
}

// Low-level module 1: Truck Transport
class TruckTransportService implements TransportService {
    @Override
    public void deliver(String cargo) {
        System.out.println("Delivering " + cargo + " by truck.");
    }
}

// Low-level module 2: Air Transport
class AirTransportService implements TransportService {
    @Override
    public void deliver(String cargo) {
        System.out.println("Delivering " + cargo + " by airplane.");
    }
}

// Low-level module 3: Ship Transport
class ShipTransportService implements TransportService {
    @Override
    public void deliver(String cargo) {
        System.out.println("Delivering " + cargo + " by ship.");
    }
}

// High-level module: Logistics Manager
class LogisticsManager {
    private TransportService transportService;

    // Dependency injected via constructor
    public LogisticsManager(TransportService transportService) {
        this.transportService = transportService;
    }

    public void shipGoods(String cargo) {
        System.out.println("Preparing to ship goods...");
        transportService.deliver(cargo);
    }
}

// Main Application
public class Main {
    public static void main(String[] args) {
        // Example: Shipping goods by truck
        TransportService truckService = new TruckTransportService();
        LogisticsManager truckLogistics = new LogisticsManager(truckService);
        truckLogistics.shipGoods("Furniture");

        // Example: Shipping goods by air
        TransportService airService = new AirTransportService();
        LogisticsManager airLogistics = new LogisticsManager(airService);
        airLogistics.shipGoods("Electronics");

        // Example: Shipping goods by ship
        TransportService shipService = new ShipTransportService();
        LogisticsManager shipLogistics = new LogisticsManager(shipService);
        shipLogistics.shipGoods("Bulk Grain");
    }
}
```

---

### Explicación del código:
1. **Abstracción:** 
   - `TransportService` define el contrato para los diferentes servicios de transporte.
2. **Módulos de bajo nivel:**
   - `TruckTransportService`, `AirTransportService` y `ShipTransportService` implementan la interfaz `TransportService` y contienen la lógica específica para cada método de transporte.
3. **Módulo de alto nivel:**
   - `LogisticsManager` se encarga de la lógica principal y utiliza un `TransportService` para entregar los bienes. No depende directamente de implementaciones concretas, sino de la abstracción `TransportService`.
4. **Inyección de dependencias:**
   - El tipo de transporte se inyecta dinámicamente en el `LogisticsManager` a través del constructor.

---

### Salida esperada:
```
Preparing to ship goods...
Delivering Furniture by truck.
Preparing to ship goods...
Delivering Electronics by airplane.
Preparing to ship goods...
Delivering Bulk Grain by ship.
```

---

### Beneficios del diseño:
1. **Desacoplamiento:** La clase `LogisticsManager` no necesita conocer los detalles de cada medio de transporte.
2. **Extensibilidad:** Si se agrega un nuevo medio de transporte (por ejemplo, tren), solo se necesita implementar la interfaz `TransportService`, sin modificar el módulo de alto nivel.
3. **Reutilización y prueba:** Es fácil probar `LogisticsManager` con diferentes tipos de transporte usando mocks o implementaciones personalizadas.

**Ventajas del enfoque DIP:**
1. **Desacoplamiento:** `Notification` no depende de implementaciones concretas, sino de la abstracción `MessageService`.
2. **Flexibilidad:** Podemos cambiar o agregar nuevas implementaciones de `MessageService` sin afectar al módulo de alto nivel.
3. **Facilidad de prueba:** Se pueden usar mocks o stubs para probar la clase `Notification`.

En este ejemplo, el uso de DIP hace que el sistema sea más extensible y fácil de mantener.