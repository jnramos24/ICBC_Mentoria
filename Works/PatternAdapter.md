Este ejercicio simulará un sistema de pagos que necesita integrar dos formas de procesadores de pagos distintos.

### Ejercicio: Sistema de Procesamiento de Pagos

Supongamos que tienes un sistema de comercio electrónico que solo acepta pagos a través de un procesador llamado `OldPaymentSystem`. Ahora quieres integrar un nuevo procesador llamado `NewPaymentProcessor` sin cambiar demasiado tu sistema actual. Para lograr esto, usarás el patrón **Adapter** y manejarás las posibles excepciones que puedan surgir durante el proceso de pago.

#### Requisitos

1. Crea una interfaz `PaymentProcessor` que defina el método `processPayment(double amount)`.
2. Implementa una clase `OldPaymentSystem` que implemente `PaymentProcessor` y simule el procesamiento de pagos con impresión en consola.
3. Crea una clase `NewPaymentProcessor` que tenga un método `makePayment(double amount)` y que pueda lanzar una excepción `PaymentFailedException`.
4. Implementa una clase `PaymentFailedException` que extienda `Exception` y represente errores de pago.
5. Implementa una clase `PaymentAdapter` que actúe como un adaptador entre `NewPaymentProcessor` y `PaymentProcessor`.
6. Escribe código en el `main` que demuestre el uso del adaptador y maneje las excepciones que puedan surgir.