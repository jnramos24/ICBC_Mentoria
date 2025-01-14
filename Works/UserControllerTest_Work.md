### **Objetivo de la Actividad**
- Probar que el controlador `UserController` del proyecto Reservation-api cumple con los contratos definidos en `UserApi`.
- Validar que los endpoints funcionan correctamente, tanto de manera aislada como en un entorno integrado con el servicio `UserService`.

---

## **Pruebas Unitarias**

En las pruebas unitarias, utilizamos `@WebMvcTest` para probar el controlador de forma aislada, con un `mock` del servicio (`UserService`).

### **Guía de Actividad**

1. **Configura la Clase de Prueba:**
   - Usa `@WebMvcTest(UserController.class)` para probar únicamente el controlador.
   - Mockea el servicio `UserService` con `@MockBean`.

2. **Pruebas Propuestas:**
   - `GET /users`: Verifica que devuelve la lista de usuarios.
   - `GET /users/{id}`: Verifica que devuelve un usuario específico por ID.

---

## **Pruebas de Integración**

En las pruebas de integración, verificamos que el controlador interactúa correctamente con el servicio y otros componentes. Usamos una base de datos en memoria (H2) para almacenar datos simulados.

### **Guía de Actividad**

1. **Configura la Clase de Prueba:**
   - Usa `@SpringBootTest` para cargar todo el contexto de Spring.
   - Usa `@AutoConfigureMockMvc` para configurar `MockMvc`.

2. **Prepara Datos de Prueba:**
   - Usa una base de datos en memoria como H2 para almacenar datos simulados.
   - Alternativamente, usa anotaciones como `@Sql` para inicializar datos antes de cada prueba.

3. **Pruebas Propuestas:**
   - `GET /users`: Verifica que devuelve la lista de usuarios almacenados.
   - `GET /users/{id}`: Verifica que devuelve un usuario específico por ID.

---

## **Casos de Prueba Propuestos**

### **Pruebas Unitarias**
1. `GET /users`
   - Devuelve una lista vacía si no hay usuarios.
   - Devuelve la lista de usuarios correctamente.
2. `GET /users/{id}`
   - Devuelve un usuario específico por ID.
   - Devuelve un error 404 si el usuario no existe.

### **Pruebas de Integración**
1. `GET /users`
   - Devuelve todos los usuarios almacenados en la base de datos.
   - Devuelve una lista vacía si no hay usuarios.
2. `GET /users/{id}`
   - Devuelve un usuario por ID.
   - Devuelve un error 404 si el usuario no existe.

---

## **Notas Adicionales**

1. **Configura una Base de Datos de Prueba (H2):**
   - Agrega estas propiedades en `src/test/resources/application-test.properties`:
     ```properties
     spring.datasource.url=jdbc:h2:mem:testdb
     spring.datasource.driver-class-name=org.h2.Driver
     spring.jpa.hibernate.ddl-auto=create-drop
     ```

2. **Valida Casos de Error:**
   - Si `UserService` lanza una excepción cuando no encuentra un usuario, el controlador debe devolver un error HTTP 404.

---