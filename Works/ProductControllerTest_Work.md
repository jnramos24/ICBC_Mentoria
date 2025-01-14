### **Objetivo de la Actividad**
Asegurarse de que:
1. El controlador `ProductController` del proyecto Reservation-api cumple con los contratos definidos en la interfaz `ProductApi`.
2. Las respuestas de los métodos cumplen con los casos de uso esperados.
3. Se validan tanto el comportamiento individual del controlador como la interacción con el servicio asociado (`ProductService`).

---

## **Pruebas Unitarias**

En las pruebas unitarias, utilizamos `@WebMvcTest` para probar el controlador de forma aislada, con un `mock` del servicio (`ProductService`).

### **Guía de Actividad**

1. **Configura la Clase de Prueba:**
   - Usa `@WebMvcTest(ProductController.class)` para cargar únicamente el controlador y las configuraciones necesarias.
   - Mockea `ProductService` con `@MockBean`.

2. **Pruebas Unitarias de los Métodos:**
   - `GET /products`: Verificar que devuelve la lista de productos.
   - `GET /products/{id}`: Verificar que devuelve un producto específico basado en el ID.

---

## **Pruebas de Integración**

### **Guía de Actividad**

1. **Configurar la Clase de Prueba:**
   - Usa `@SpringBootTest` para cargar todo el contexto de Spring.
   - Usa `@AutoConfigureMockMvc` para configurar `MockMvc`.

2. **Prepara Datos de Prueba:**
   - Usa una base de datos en memoria como H2 para cargar datos simulados.
   - Alternativamente, usa anotaciones como `@Sql` para inicializar datos antes de las pruebas.

3. **Prueba de los Métodos:**
   - `GET /products`: Verificar que devuelve los productos de la base de datos.
   - `GET /products/{id}`: Verificar que devuelve un producto por su ID.

---

## **Casos de Prueba Propuestos**

### **Pruebas Unitarias**
1. `GET /products`
   - Verifica que devuelve una lista vacía si no hay productos.
   - Verifica que devuelve la lista de productos correctamente.
2. `GET /products/{id}`
   - Verifica que devuelve un producto específico por ID.
   - Verifica que devuelve un error 404 si el producto no existe.

### **Pruebas de Integración**
1. `GET /products`
   - Verifica que devuelve los productos de la base de datos.
   - Verifica que devuelve una lista vacía si no hay productos.
2. `GET /products/{id}`
   - Verifica que devuelve un producto por su ID.
   - Verifica que devuelve un error 404 si el producto no existe.

---

## **Notas Adicionales**

1. **Valida los casos de error:**
   - Por ejemplo, si `ProductService` lanza una excepción para un ID inexistente, el controlador debería devolver un código HTTP 404.

2. **Configura la Base de Datos de Pruebas:**
   - Usa una base de datos en memoria para los tests de integración:
     ```properties
     spring.datasource.url=jdbc:h2:mem:testdb
     spring.datasource.driver-class-name=org.h2.Driver
     spring.jpa.hibernate.ddl-auto=create-drop
     ```

---