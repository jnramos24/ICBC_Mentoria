Supongamos que tienes varias entidades en tu aplicación, como `Usuario` y `Producto`. Necesitas implementar operaciones CRUD para cada entidad y deseas evitar la duplicación de código.

### Código del Proyecto

#### Entidades
**Usuario.java**
```java
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
```

**Producto.java**
```java
package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double precio;

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
```

#### Repositorio Genérico
**BaseRepository.java**
```java
package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository<T, ID> extends JpaRepository<T, ID> {
}
```

#### Servicio Genérico
**BaseService.java**
```java
package com.example.demo.service;

import com.example.demo.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class BaseService<T, ID> {

    @Autowired
    protected BaseRepository<T, ID> repository;

    public List<T> findAll() {
        return repository.findAll();
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
```

#### Servicios Específicos
**UsuarioService.java**
```java
package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService extends BaseService<Usuario, Long> {
}
```

**ProductoService.java**
```java
package com.example.demo.service;

import com.example.demo.entity.Producto;
import com.example.demo.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoService extends BaseService<Producto, Long> {
}
```

#### Controladores
**UsuarioController.java**
```java
package com.example.demo.controller;

import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.findAll();
    }

    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario) {
        return usuarioService.save(usuario);
    }
}
```

**ProductoController.java**
```java
package com.example.demo.controller;

import com.example.demo.entity.Producto;
import com.example.demo.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAllProductos() {
        return productoService.findAll();
    }

    @PostMapping
    public Producto createProducto(@RequestBody Producto producto) {
        return productoService.save(producto);
    }
}
```

### Ventajas del Uso de Genéricos
1. **Reutilización del Código**: La lógica común de los servicios se implementa una sola vez en `BaseService`.
2. **Facilidad de Mantenimiento**: Si necesitas cambiar la lógica CRUD, lo haces en un solo lugar.
3. **Flexibilidad**: Puedes extender `BaseService` para agregar lógica específica para cada entidad.