Guía para testear controladores de spring: `POST` y `PUT`

---

## **1. Configuración Inicial**

Antes de comenzar a probar, asegúrate de que tu proyecto de **Spring Boot** tiene las dependencias necesarias.

### **Dependencias en `pom.xml` (Maven)**:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>com.jayway.jsonpath</groupId>
    <artifactId>json-path</artifactId>
    <scope>test</scope>
</dependency>
```

### **Dependencias en `build.gradle` (Gradle)**:
```gradle
testImplementation 'org.springframework.boot:spring-boot-starter-test'
testImplementation 'com.jayway.jsonpath:json-path'
```

---

## **2. Creación del Controlador de Ejemplo**

Para los ejemplos, usaremos un **controlador de usuarios (`UserController`)** con dos endpoints:
- **`POST /users`** → Para crear un usuario.
- **`PUT /users/{id}`** → Para actualizar un usuario.

### **Modelo (`UserDto`)**
```java
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String name;
    private String email;
}
```

### **Servicio (`UserService`)**
```java
@Service
public class UserService {

    private final Map<Long, UserDto> userRepository = new HashMap<>();

    public UserDto createUser(UserDto userDto) {
        userDto.setId((long) (userRepository.size() + 1));
        userRepository.put(userDto.getId(), userDto);
        return userDto;
    }

    public UserDto updateUser(Long id, UserDto userDto) {
        if (!userRepository.containsKey(id)) {
            throw new RuntimeException("User not found");
        }
        userDto.setId(id);
        userRepository.put(id, userDto);
        return userDto;
    }
}
```

### **Controlador (`UserController`)**
```java
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.updateUser(id, userDto));
    }
}
```

---

## **3. Pruebas Unitarias (`@WebMvcTest`)**
Las pruebas unitarias **mockean el servicio (`UserService`)** para probar exclusivamente el controlador.

1. **Configurar la clase de prueba**:
   - Usa `@WebMvcTest(UserController.class)`.
   - Mockea `UserService` con `@MockBean`.

2. **Escribir pruebas unitarias** para:
   - `POST /users`: Debe retornar `201 Created` con el usuario creado.
   - `PUT /users/{id}`: Debe retornar `200 OK` con el usuario actualizado.
   - **Manejo de errores**: Prueba que el `PUT` devuelva `404 Not Found` si el usuario no existe.

---

### **Ejemplo de Código para Pruebas Unitarias**
```java
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateUserSuccessfully() throws Exception {
        UserDto userDto = new UserDto(null, "John Doe", "john.doe@example.com");
        UserDto createdUser = new UserDto(1L, "John Doe", "john.doe@example.com");

        Mockito.when(userService.createUser(Mockito.any(UserDto.class))).thenReturn(createdUser);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Doe"));
    }

    @Test
    void shouldUpdateUserSuccessfully() throws Exception {
        UserDto userDto = new UserDto(null, "John Updated", "john.updated@example.com");
        UserDto updatedUser = new UserDto(1L, "John Updated", "john.updated@example.com");

        Mockito.when(userService.updateUser(Mockito.eq(1L), Mockito.any(UserDto.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John Updated"));
    }

    @Test
    void shouldReturnNotFoundWhenUpdatingNonExistentUser() throws Exception {
        UserDto userDto = new UserDto(null, "John Updated", "john.updated@example.com");

        Mockito.when(userService.updateUser(Mockito.eq(99L), Mockito.any(UserDto.class)))
                .thenThrow(new RuntimeException("User not found"));

        mockMvc.perform(put("/users/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isNotFound());
    }
}
```

---

## **4. Pruebas de Integración (`@SpringBootTest`)**
Las pruebas de integración **cargan el contexto completo de la aplicación** y prueban la interacción real entre el controlador y el servicio.

1. **Configurar la clase de prueba**:
   - Usa `@SpringBootTest` para cargar todo el contexto de Spring.
   - Usa `@AutoConfigureMockMvc` para configurar `MockMvc`.

2. **Prepara datos de prueba**:
   - Configura una base de datos en memoria (`H2`).
   - Usa `@BeforeEach` para limpiar datos antes de cada prueba.

3. **Escribir pruebas de integración** para:
   - `POST /users`: Verificar que se crea un usuario en la base de datos.
   - `PUT /users/{id}`: Verificar que se actualiza un usuario en la base de datos.

---

### **Ejemplo de Código para Pruebas de Integración**
```java
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldCreateUser() throws Exception {
        UserDto userDto = new UserDto(null, "Jane Doe", "jane.doe@example.com");

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Jane Doe"));
    }

    @Test
    void shouldUpdateUser() throws Exception {
        UserDto userDto = new UserDto(null, "Jane Updated", "jane.updated@example.com");

        mockMvc.perform(put("/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Updated"));
    }
}
```

---