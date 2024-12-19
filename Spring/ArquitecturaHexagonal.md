La **arquitectura hexagonal**, también conocida como **Arquitectura de Puertos y Adaptadores**, es un enfoque de diseño de software que busca separar las preocupaciones de negocio (núcleo o dominio) de las dependencias externas (como bases de datos, interfaces de usuario, APIs, etc.). En el contexto de **Spring Framework**, esta arquitectura permite construir aplicaciones más flexibles, modulares y fáciles de mantener.

Explicación adaptada al contexto de Spring:

---

### **Componentes Principales de la Arquitectura Hexagonal**
1. **Dominio (Core de la Aplicación):**
   - Es el núcleo de la aplicación, donde residen las reglas de negocio y la lógica principal.
   - No debe tener dependencias hacia el mundo externo (bases de datos, frameworks, etc.).
   - En Spring, este sería un paquete donde defines tus entidades, servicios de dominio y validaciones.

2. **Puertos (Ports):**
   - Interfaces que definen cómo interactuar con el núcleo de la aplicación.
   - Existen dos tipos:
     - **Puertos de Entrada:** Define cómo el mundo externo puede interactuar con el dominio (por ejemplo, controladores, eventos).
     - **Puertos de Salida:** Define cómo el dominio interactúa con sistemas externos (como bases de datos o APIs).
   - En Spring, estas interfaces suelen representarse como servicios o repositorios.

3. **Adaptadores (Adapters):**
   - Implementaciones concretas de los puertos.
   - Existen dos tipos:
     - **Adaptadores de Entrada:** Traducen las solicitudes del mundo externo (como HTTP, eventos) hacia los puertos de entrada.
       - Ejemplo: Controladores REST en Spring (`@RestController`).
     - **Adaptadores de Salida:** Implementan la interacción con sistemas externos (como una base de datos o servicios de terceros).
       - Ejemplo: Repositorios JPA (`@Repository`) o servicios externos.

4. **Capas Externas:**
   - Son las infraestructuras o herramientas que interactúan con los adaptadores, como bases de datos, interfaces de usuario, APIs externas, etc.

---

### **Cómo Implementarlo en Spring**
Un proyecto en Spring basado en arquitectura hexagonal puede estructurarse de esta manera:

#### 1. **Dominio (`domain`)**
   - **Entidades:** Clases que representan las reglas de negocio (POJOs).
   - **Servicios de Dominio:** Implementan las reglas de negocio principales.
   - **Interfaces de los Puertos (Entrada y Salida):** Definen cómo interactuar con el dominio.
   ```java
   public interface OrderService {
       void placeOrder(Order order);
   }

   public interface PaymentGateway {
       boolean processPayment(Payment payment);
   }
   ```

#### 2. **Aplicación (`application`)**
   - **Adaptadores de Entrada:** Implementan la lógica que conecta el mundo externo con el dominio.
   ```java
   @RestController
   public class OrderController {
       private final OrderService orderService;

       public OrderController(OrderService orderService) {
           this.orderService = orderService;
       }

       @PostMapping("/orders")
       public ResponseEntity<String> createOrder(@RequestBody Order order) {
           orderService.placeOrder(order);
           return ResponseEntity.ok("Order placed successfully!");
       }
   }
   ```

   - **Adaptadores de Salida:** Implementan las interfaces de los puertos de salida.
   ```java
   @Repository
   public class PaymentGatewayImpl implements PaymentGateway {
       @Override
       public boolean processPayment(Payment payment) {
           // Interactúa con un API externo de pago
           return true;
       }
   }
   ```

#### 3. **Infraestructura (`infrastructure`)**
   - Aquí colocas configuraciones específicas de Spring, como `DataSource`, configuraciones de seguridad, etc.

---

### **Beneficios**
- **Separación de responsabilidades:** Aislando la lógica de negocio de los detalles técnicos.
- **Modularidad:** Es fácil reemplazar adaptadores sin tocar el núcleo.
- **Testabilidad:** Puedes probar el núcleo de la aplicación sin depender de componentes externos.
- **Flexibilidad:** Facilita cambios en la tecnología externa (como cambiar de base de datos o frameworks).

---

### **Ejemplo Visual**

```plaintext
                      +---------------------+
  HTTP Request  ----> | Adaptador de Entrada| ----+
                      +---------------------+     |
                                                 (Puerto de Entrada)
                      +---------------------+     |
                      |     Dominio (Core)  | <---+
                      +---------------------+     |
                        |                    (Puerto de Salida)
                        v
                      +---------------------+
                      | Adaptador de Salida | ----> Interacción Externa
                      +---------------------+
```

Con esta estructura en Spring, puedes tener una aplicación limpia, organizada y preparada para crecer de forma sostenible.

Les dejo un ejemplo más desarrollado:

### **Caso de Uso**: 
Gestión de órdenes de compra con interacción con un servicio externo de pagos.

### **Estructura del Proyecto**
```plaintext
src/main/java/com/example/hexagonalarchitecture
    ├── domain
    │   ├── model
    │   │   ├── Order.java
    │   │   ├── Payment.java
    │   ├── port
    │   │   ├── in
    │   │   │   ├── OrderService.java
    │   │   ├── out
    │   │       ├── PaymentGateway.java
    ├── application
    │   ├── service
    │   │   ├── OrderServiceImpl.java
    │   ├── adapter
    │   │   ├── in
    │   │   │   ├── OrderController.java
    │   │   ├── out
    │   │       ├── PaymentGatewayAdapter.java
    ├── infrastructure
    │   ├── config
    │       ├── ApplicationConfig.java
```

---

### **Código**

#### 1. **Dominio (`domain`)**

##### a) Modelos
**Order.java**
```java
package com.example.hexagonalarchitecture.domain.model;

public class Order {
    private String id;
    private double amount;

    // Constructor, getters, setters
    public Order(String id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    // Getters y Setters
}
```

**Payment.java**
```java
package com.example.hexagonalarchitecture.domain.model;

public class Payment {
    private String orderId;
    private double amount;

    // Constructor, getters, setters
    public Payment(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
    }
}
```

##### b) Puertos
**OrderService.java** (Puerto de Entrada)
```java
package com.example.hexagonalarchitecture.domain.port.in;

import com.example.hexagonalarchitecture.domain.model.Order;

public interface OrderService {
    void placeOrder(Order order);
}
```

**PaymentGateway.java** (Puerto de Salida)
```java
package com.example.hexagonalarchitecture.domain.port.out;

import com.example.hexagonalarchitecture.domain.model.Payment;

public interface PaymentGateway {
    boolean processPayment(Payment payment);
}
```

---

#### 2. **Aplicación (`application`)**

##### a) Servicio de Dominio
**OrderServiceImpl.java**
```java
package com.example.hexagonalarchitecture.application.service;

import com.example.hexagonalarchitecture.domain.model.Order;
import com.example.hexagonalarchitecture.domain.model.Payment;
import com.example.hexagonalarchitecture.domain.port.in.OrderService;
import com.example.hexagonalarchitecture.domain.port.out.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final PaymentGateway paymentGateway;

    public OrderServiceImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public void placeOrder(Order order) {
        Payment payment = new Payment(order.getId(), order.getAmount());
        boolean isPaymentProcessed = paymentGateway.processPayment(payment);

        if (!isPaymentProcessed) {
            throw new RuntimeException("Payment failed for order: " + order.getId());
        }

        System.out.println("Order placed successfully: " + order.getId());
    }
}
```

---

#### 3. **Adaptadores (`adapter`)**

##### a) Adaptador de Entrada
**OrderController.java**
```java
package com.example.hexagonalarchitecture.application.adapter.in;

import com.example.hexagonalarchitecture.domain.model.Order;
import com.example.hexagonalarchitecture.domain.port.in.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<String> createOrder(@RequestBody Order order) {
        orderService.placeOrder(order);
        return ResponseEntity.ok("Order placed successfully!");
    }
}
```

##### b) Adaptador de Salida
**PaymentGatewayAdapter.java**
```java
package com.example.hexagonalarchitecture.application.adapter.out;

import com.example.hexagonalarchitecture.domain.model.Payment;
import com.example.hexagonalarchitecture.domain.port.out.PaymentGateway;
import org.springframework.stereotype.Component;

@Component
public class PaymentGatewayAdapter implements PaymentGateway {
    @Override
    public boolean processPayment(Payment payment) {
        System.out.println("Processing payment for order: " + payment.getOrderId());
        return true; // Simula que el pago se realizó correctamente
    }
}
```

---

#### 4. **Infraestructura (`infrastructure`)**
**ApplicationConfig.java**
```java
package com.example.hexagonalarchitecture.infrastructure.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    // Aquí puedes definir configuraciones específicas de Spring
}
```

---

### **Diagrama Visual**

```plaintext
                  +----------------------------------+
                  |          Adaptador de Entrada   |
   HTTP Request ->|     OrderController             |
                  +----------------------------------+
                              |
                              v
                  +----------------------------------+
                  |         Núcleo del Dominio      |
                  | OrderServiceImpl                |
                  | - OrderService (Puerto Entrada) |
                  | - PaymentGateway (Puerto Salida)|
                  +----------------------------------+
                              |
                              v
                  +----------------------------------+
                  |          Adaptador de Salida    |
                  |  PaymentGatewayAdapter          |
                  +----------------------------------+
                              |
                              v
                  +----------------------------------+
                  |       Servicio de Pago Externo  |
                  +----------------------------------+
```

---

### **Ejecutar la Aplicación**
1. Levanta el servidor Spring Boot.
2. Envía una solicitud `POST` al endpoint `/orders` con el siguiente cuerpo:
   ```json
   {
       "id": "123",
       "amount": 100.0
   }
   ```
3. En la consola, verás mensajes simulando el procesamiento del pago y la confirmación del pedido.
