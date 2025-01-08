

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