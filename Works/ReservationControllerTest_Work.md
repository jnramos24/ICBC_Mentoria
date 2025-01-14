### **Objetivo de la Actividad**
Testear el comportamiento del controlador `ReservationController` del proyecto Reservation-api asegurándose de que:
1. Los métodos devuelvan las respuestas correctas según los casos de prueba definidos.
2. Se valide la interacción entre el controlador y el servicio asociado (`ReservationService`).
3. Se realicen tanto **pruebas unitarias** como **pruebas de integración**.

---

## **1. Configuración Inicial**

### **Dependencias**
Asegúrate de que las siguientes dependencias están incluidas en el archivo `pom.xml` (Maven) o `build.gradle` (Gradle).

#### Maven:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <scope>test</scope>
</dependency>
```

---

### **Estructura del Proyecto**
Organiza las pruebas de la siguiente manera:
```
src
├── main
│   └── java/com/example/project
│       ├── controller/ReservationController.java
│       ├── service/ReservationService.java
│       └── model/ReservationEntity.java
└── test
    └── java/com/example/project
        ├── controller/ReservationControllerTest.java   (Pruebas Unitarias)
        └── integration/ReservationControllerIntegrationTest.java   (Pruebas de Integración)
```

---

## **2. Pruebas Unitarias**

En las pruebas unitarias, usamos **`@WebMvcTest`** para aislar el controlador y mockear el servicio.

### **Guía de Actividad**

1. **Configura la clase de prueba:**
   - Usa la anotación `@WebMvcTest(ReservationController.class)` para inicializar el controlador.
   - Mockea `ReservationService` con `@MockBean`.

2. **Prueba los endpoints del controlador:**
   - `GET /reservations`: Verifica que devuelve una lista de reservas.
   - `GET /reservations/{reservationId}`: Verifica la búsqueda por ID.
   - `GET /reservations/paginated`: Verifica que soporta paginación.
   - `GET /reservations/search/{value}`: Prueba que busca correctamente tanto por `hotelId` como por `status`.

---

## **3. Pruebas de Integración**

En las pruebas de integración, cargamos el contexto completo de la aplicación y verificamos que el controlador interactúe correctamente con otros componentes.

### **Guía de Actividad**

1. **Configura la clase de prueba:**
   - Usa la anotación `@SpringBootTest` y `@AutoConfigureMockMvc`.
   - Prepara una base de datos en memoria (como H2) para almacenar datos.
   ```properties
     spring.datasource.url=jdbc:h2:mem:testdb
     spring.datasource.driver-class-name=org.h2.Driver
     spring.jpa.hibernate.ddl-auto=create-drop
     ```
   - Recuerda agregar la dependencia necesaria para esto con el scope en `test`.

2. **Prepara datos de prueba:**
   - Usa herramientas como `TestEntityManager` o `@Sql` para inicializar datos en la base de datos antes de cada prueba.

3. **Prueba los endpoints con datos reales:**
   - Verifica que las respuestas contienen los datos esperados.

---

## **4. Casos de Prueba Propuestos**

1. **Pruebas Unitarias:**
   - `GET /reservations`: Devuelve todas las reservas.
   - `GET /reservations/{reservationId}`: Devuelve una reserva específica.
   - `GET /reservations/search/{value}`:
     - Buscar por `hotelId`.
     - Buscar por `status`.
   - `GET /reservations/paginated`: Devuelve resultados paginados.

2. **Pruebas de Integración:**
   - Valida que todos los endpoints funcionan con datos reales.
   - Configura datos en la base de datos en memoria y verifica que las respuestas coincidan.