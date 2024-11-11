### Ejercicio: Sistema de Gestión de Pedidos 
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