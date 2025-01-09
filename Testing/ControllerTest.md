

Guía para testear controladores de spring:

## 1. **Preparativos Iniciales**

Antes de empezar, asegúrate de tener las siguientes dependencias en tu proyecto `Maven` o `Gradle` para realizar pruebas:

### Dependencias para Maven:
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

### Dependencias para Gradle:
```gradle
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'org.mockito:mockito-core'
```

Estas librerías incluyen herramientas como JUnit, Spring Test y Mockito.

---

## 2. **Tipos de Pruebas**

1. **Unit Testing**: Pruebas individuales de los métodos del controlador.
2. **Integration Testing**: Verificar cómo el controlador interactúa con otros componentes, como servicios o repositorios.

---

## 3. **Configuración Básica**

En Spring, usamos principalmente dos enfoques para probar controladores:

### 3.1. **Usando `MockMvc` para pruebas unitarias:**

`MockMvc` permite simular solicitudes HTTP hacia los controladores sin necesidad de desplegar el servidor.

#### Ejemplo de configuración:
```java
@WebMvcTest(YourController.class)
class YourControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private YourService yourService;

    @Test
    void shouldReturnExpectedResponse() throws Exception {
        // Configurar el comportamiento del servicio mock
        Mockito.when(yourService.getData()).thenReturn("Hello, World!");

        // Simular una solicitud HTTP
        mockMvc.perform(get("/api/endpoint"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello, World!"));
    }
}
```

---

### 3.2. **Pruebas de integración completas con el contexto de Spring:**

En este caso, cargamos el contexto completo de la aplicación para verificar la interacción real.

#### Ejemplo de configuración:
```java
@SpringBootTest
@AutoConfigureMockMvc
class YourControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldHandleRequestProperly() throws Exception {
        mockMvc.perform(get("/api/endpoint"))
                .andExpect(status().isOk())
                .andExpect(content().string("Expected response"));
    }
}
```

---

## 4. **Estructura de Pruebas**

### 4.1. Configurar `MockMvc`:
```java
mockMvc.perform(get("/api/endpoint"))
       .andExpect(status().isOk())
       .andExpect(content().string("Expected Response"));
```

### 4.2. Pruebas con parámetros:
```java
mockMvc.perform(get("/api/endpoint").param("id", "123"))
       .andExpect(status().isOk())
       .andExpect(jsonPath("$.name").value("Test Name"));
```

### 4.3. Pruebas con cuerpo JSON:
```java
mockMvc.perform(post("/api/endpoint")
       .contentType(MediaType.APPLICATION_JSON)
       .content("{\"key\":\"value\"}"))
       .andExpect(status().isCreated());
```

---

## 5. **Herramientas y Buenas Prácticas**

1. **Mockito:** Para mockear servicios.
2. **JsonPath:** Para validar estructuras JSON en respuestas.
3. **Hamcrest Matchers:** Para realizar validaciones más avanzadas.
4. **Pruebas Negativas:** Asegúrate de probar casos en los que el sistema debería fallar.

---

## 6. **Ejemplo Completo**

Supongamos que tienes un controlador:
```java
@RestController
@RequestMapping("/api")
public class YourController {

    private final YourService yourService;

    public YourController(YourService yourService) {
        this.yourService = yourService;
    }

    @GetMapping("/data")
    public ResponseEntity<String> getData() {
        return ResponseEntity.ok(yourService.getData());
    }
}
```

### Prueba unitaria:
```java
@WebMvcTest(YourController.class)
class YourControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private YourService yourService;

    @Test
    void shouldReturnDataFromService() throws Exception {
        Mockito.when(yourService.getData()).thenReturn("Mocked Data");

        mockMvc.perform(get("/api/data"))
                .andExpect(status().isOk())
                .andExpect(content().string("Mocked Data"));
    }
}
```

### Prueba de integración:
```java
@SpringBootTest
@AutoConfigureMockMvc
class YourControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnExpectedResponse() throws Exception {
        mockMvc.perform(get("/api/data"))
                .andExpect(status().isOk())
                .andExpect(content().string("Expected Data"));
    }
}
```

Si tu controlador devuelve objetos que anidan otros objetos, puedes usar **JsonPath** para validar partes específicas de la estructura JSON.

---

## Ejemplo de Controlador con Objetos Anidados

Supongamos que tienes un controlador que devuelve un objeto anidado:

### Modelo:
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private int age;
    private Address address;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String street;
    private String city;
}
```

### Controlador:
```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        Address address = new Address("123 Main St", "Springfield");
        User user = new User("John Doe", 30, address);
        return ResponseEntity.ok(user);
    }
}
```

---

## Prueba del Controlador con Objetos Anidados

Usaremos **MockMvc** y **JsonPath** para validar partes específicas del objeto.

### Prueba:
```java
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnNestedObject() throws Exception {
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.age").value(30))
                .andExpect(jsonPath("$.address.street").value("123 Main St"))
                .andExpect(jsonPath("$.address.city").value("Springfield"));
    }
}
```

### Explicación de `jsonPath`:

1. `$.name`: Accede al campo `name` del objeto principal.
2. `$.address.street`: Navega al objeto `address` y verifica el campo `street`.
3. `$.address.city`: Verifica el campo `city` dentro del objeto `address`.

---

## Validar Objetos Anidados Complejos

Si necesitas validar toda la estructura JSON, puedes comparar directamente la respuesta con un JSON esperado.

### Prueba con comparación JSON:
```java
@Test
void shouldReturnCompleteJsonResponse() throws Exception {
    String expectedJson = """
        {
            "name": "John Doe",
            "age": 30,
            "address": {
                "street": "123 Main St",
                "city": "Springfield"
            }
        }
    """;

    mockMvc.perform(get("/api/users/1"))
            .andExpect(status().isOk())
            .andExpect(content().json(expectedJson));
}
```

---

## Pruebas de Serialización/Deserialización con `ObjectMapper`

Es una buena práctica validar que tu modelo puede ser correctamente serializado y deserializado.

```java
@Test
void shouldSerializeAndDeserializeUser() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();

    // Crear objeto original
    Address address = new Address("123 Main St", "Springfield");
    User user = new User("John Doe", 30, address);

    // Serializar a JSON
    String json = objectMapper.writeValueAsString(user);

    // Deserializar de JSON
    User deserializedUser = objectMapper.readValue(json, User.class);

    assertEquals(user, deserializedUser);
}
```

---

## Pruebas de Integración para Objetos Anidados

Si necesitas hacer pruebas de integración que incluyan objetos anidados, el enfoque es similar.

### Prueba de Integración:
```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnUserWithNestedAddress() throws Exception {
        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.address.street").value("123 Main St"))
                .andExpect(jsonPath("$.address.city").value("Springfield"));
    }
}
```

 