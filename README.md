# -_proyecto_API_REST_de_productos_- :.
# API REST de Productos — Java Spring Boot:

<img width="1254" height="1254" alt="image" src="https://github.com/user-attachments/assets/73b407f6-846a-4d5e-8715-98bd086321dd" />

<img width="2546" height="1076" alt="image" src="https://github.com/user-attachments/assets/2dd2a021-17e7-48af-af85-56768b8d2dfe" />         

<img width="2553" height="1074" alt="image" src="https://github.com/user-attachments/assets/b5104b4e-f6fb-46ee-a43d-897a750429ce" />

<img width="2545" height="1077" alt="image" src="https://github.com/user-attachments/assets/e397b420-12bd-44c2-b558-4d99b8059d7b" />         

```

API REST básica desarrollada con **Java 21**, **Spring Boot**, **Maven** e **IntelliJ IDEA**.

El proyecto permite realizar operaciones CRUD sobre productos y probar los diferentes endpoints utilizando **Postman**.

---

## 1. Tecnologías utilizadas

* Java 21
* Spring Boot 3.5.5
* Maven
* IntelliJ IDEA
* Spring Web
* Postman
* JSON
* API REST

---

## 2. Funcionalidades

La API permite realizar las siguientes operaciones:

| Método | Endpoint           | Función             |
| ------ | ------------------ | ------------------- |
| GET    | `/api/productos`   | Listar productos    |
| GET    | `/api/productos/1` | Buscar producto     |
| POST   | `/api/productos`   | Crear producto      |
| PUT    | `/api/productos/1` | Actualizar producto |
| DELETE | `/api/productos/1` | Eliminar producto   |

> **Nota:** Los datos se almacenan temporalmente en memoria utilizando un `ArrayList`. Al reiniciar la aplicación, los datos vuelven a los valores iniciales.

---

# 3. Estructura del proyecto

```text
rest-productos/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── example/
│                   └── restproductos/
│                       │
│                       ├── RestProductosApplication.java
│                       │
│                       ├── controller/
│                       │   └── ProductoController.java
│                       │
│                       └── model/
│                           └── Producto.java
│
├── pom.xml
│
└── README.md
```

---

# 4. Crear el proyecto en IntelliJ IDEA

Abrir IntelliJ IDEA y seleccionar:

```text
New Project
```

Configurar:

```text
Name: rest-productos
Language: Java
Build System: Maven
JDK: 21
```

Si se utiliza Spring Initializr:

```text
Group:
com.example

Artifact:
rest-productos

Name:
rest-productos

Package name:
com.example.restproductos

Java:
21
```

Agregar la dependencia:

```text
Spring Web
```

---

# 5. Archivo `pom.xml`

El archivo `pom.xml` contiene la configuración de Maven y la dependencia de Spring Web.

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="
         http://maven.apache.org/POM/4.0.0
         https://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.5</version>
        <relativePath/>
    </parent>

    <groupId>com.example</groupId>

    <artifactId>rest-productos</artifactId>

    <version>0.0.1-SNAPSHOT</version>

    <name>rest-productos</name>

    <description>
        API REST básica de productos con Spring Boot
    </description>

    <properties>

        <java.version>21</java.version>

    </properties>

    <dependencies>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

    </dependencies>

    <build>

        <plugins>

            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>

        </plugins>

    </build>

</project>
```

---

# 6. Modelo `Producto.java`

Ubicación:

```text
src/main/java/com/example/restproductos/model/Producto.java
```

Código completo:

```java
package com.example.restproductos.model;

public class Producto {

    private Long id;
    private String nombre;
    private double precio;

    public Producto() {
    }

    public Producto(Long id, String nombre, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
```

---

# 7. Controlador `ProductoController.java`

Ubicación:

```text
src/main/java/com/example/restproductos/controller/ProductoController.java
```

Código completo:

```java
package com.example.restproductos.controller;

import com.example.restproductos.model.Producto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final List<Producto> productos = new ArrayList<>();

    public ProductoController() {

        productos.add(
                new Producto(
                        1L,
                        "Laptop",
                        2500000
                )
        );

        productos.add(
                new Producto(
                        2L,
                        "Mouse",
                        80000
                )
        );

        productos.add(
                new Producto(
                        3L,
                        "Teclado",
                        120000
                )
        );
    }

    // ==========================================
    // GET - Listar productos
    // ==========================================

    @GetMapping
    public List<Producto> listar() {

        return productos;
    }

    // ==========================================
    // GET - Buscar producto por ID
    // ==========================================

    @GetMapping("/{id}")
    public Producto buscar(
            @PathVariable Long id) {

        return productos.stream()
                .filter(
                        p -> p.getId().equals(id)
                )
                .findFirst()
                .orElse(null);
    }

    // ==========================================
    // POST - Crear producto
    // ==========================================

    @PostMapping
    public Producto crear(
            @RequestBody Producto producto) {

        producto.setId(
                (long) (productos.size() + 1)
        );

        productos.add(producto);

        return producto;
    }

    // ==========================================
    // PUT - Actualizar producto
    // ==========================================

    @PutMapping("/{id}")
    public Producto actualizar(
            @PathVariable Long id,
            @RequestBody Producto producto) {

        Producto existente = buscar(id);

        if (existente != null) {

            existente.setNombre(
                    producto.getNombre()
            );

            existente.setPrecio(
                    producto.getPrecio()
            );
        }

        return existente;
    }

    // ==========================================
    // DELETE - Eliminar producto
    // ==========================================

    @DeleteMapping("/{id}")
    public String eliminar(
            @PathVariable Long id) {

        Producto producto = buscar(id);

        if (producto != null) {

            productos.remove(producto);

            return "Producto eliminado correctamente";
        }

        return "Producto no encontrado";
    }
}
```

---

# 8. Clase principal

Archivo:

```text
src/main/java/com/example/restproductos/RestProductosApplication.java
```

Código completo:

```java
package com.example.restproductos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestProductosApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                RestProductosApplication.class,
                args
        );
    }
}
```

---

# 9. Ejecutar la aplicación

Desde IntelliJ IDEA abrir:

```text
RestProductosApplication.java
```

Ejecutar:

```text
Run 'RestProductosApplication'
```

Si la aplicación inicia correctamente, se mostrará un mensaje similar a:

```text
Started RestProductosApplication
```

Spring Boot utilizará por defecto el puerto:

```text
8080
```

Por lo tanto, la dirección base será:

```text
http://localhost:8080
```

La API estará disponible en:

```text
http://localhost:8080/api/productos
```

---

# 10. Pruebas con Postman

Para realizar las pruebas se puede utilizar:

```text
Postman
```

La URL base será:

```text
http://localhost:8080/api/productos
```

---

# 11. GET — Listar productos

### Método

```text
GET
```

### URL

```text
http://localhost:8080/api/productos
```

### Respuesta

```json
[
    {
        "id": 1,
        "nombre": "Laptop",
        "precio": 2500000.0
    },
    {
        "id": 2,
        "nombre": "Mouse",
        "precio": 80000.0
    },
    {
        "id": 3,
        "nombre": "Teclado",
        "precio": 120000.0
    }
]
```

---

# 12. GET — Buscar producto

Permite buscar un producto utilizando su identificador.

### Método

```text
GET
```

### URL

```text
http://localhost:8080/api/productos/1
```

### Respuesta

```json
{
    "id": 1,
    "nombre": "Laptop",
    "precio": 2500000.0
}
```

También se puede probar:

```text
GET http://localhost:8080/api/productos/2
```

Resultado:

```json
{
    "id": 2,
    "nombre": "Mouse",
    "precio": 80000.0
}
```

---

# 13. POST — Crear producto

Permite registrar un nuevo producto.

### Método

```text
POST
```

### URL

```text
http://localhost:8080/api/productos
```

En Postman seleccionar:

```text
Body
    ↓
raw
    ↓
JSON
```

Enviar:

```json
{
    "nombre": "Monitor",
    "precio": 850000
}
```

### Respuesta

```json
{
    "id": 4,
    "nombre": "Monitor",
    "precio": 850000.0
}
```

---

# 14. POST — Otro ejemplo

Se puede crear otro producto:

```json
{
    "nombre": "Impresora",
    "precio": 650000
}
```

Respuesta:

```json
{
    "id": 5,
    "nombre": "Impresora",
    "precio": 650000.0
}
```

---

# 15. PUT — Actualizar producto

Permite modificar un producto existente.

### Método

```text
PUT
```

### URL

```text
http://localhost:8080/api/productos/1
```

Seleccionar:

```text
Body
    ↓
raw
    ↓
JSON
```

Enviar:

```json
{
    "nombre": "Laptop Lenovo",
    "precio": 2800000
}
```

### Respuesta

```json
{
    "id": 1,
    "nombre": "Laptop Lenovo",
    "precio": 2800000.0
}
```

---

# 16. DELETE — Eliminar producto

Permite eliminar un producto por su ID.

### Método

```text
DELETE
```

### URL

```text
http://localhost:8080/api/productos/2
```

No es necesario enviar Body.

### Respuesta

```text
Producto eliminado correctamente
```

---

# 17. DELETE — Producto inexistente

Si se intenta eliminar un producto que no existe:

```text
DELETE
http://localhost:8080/api/productos/99
```

La respuesta será:

```text
Producto no encontrado
```

---

# 18. Flujo de pruebas recomendado

Se recomienda realizar las operaciones en este orden:

### 1. Listar

```text
GET /api/productos
```

### 2. Buscar

```text
GET /api/productos/1
```

### 3. Crear

```text
POST /api/productos
```

Body:

```json
{
    "nombre": "Monitor",
    "precio": 850000
}
```

### 4. Listar nuevamente

```text
GET /api/productos
```

### 5. Actualizar

```text
PUT /api/productos/1
```

Body:

```json
{
    "nombre": "Laptop Lenovo",
    "precio": 2800000
}
```

### 6. Eliminar

```text
DELETE /api/productos/2
```

### 7. Verificar

```text
GET /api/productos
```

---

# 19. Resumen de endpoints

| Operación  | Método | URL                                     |
| ---------- | ------ | --------------------------------------- |
| Listar     | GET    | `http://localhost:8080/api/productos`   |
| Buscar     | GET    | `http://localhost:8080/api/productos/1` |
| Crear      | POST   | `http://localhost:8080/api/productos`   |
| Actualizar | PUT    | `http://localhost:8080/api/productos/1` |
| Eliminar   | DELETE | `http://localhost:8080/api/productos/1` |

---

# 20. Conceptos utilizados

Este proyecto permite practicar los principales conceptos de una API REST:

### `@RestController`

Indica que la clase funciona como controlador REST.

```java
@RestController
```

### `@RequestMapping`

Define la ruta base del controlador.

```java
@RequestMapping("/api/productos")
```

### `@GetMapping`

Procesa solicitudes HTTP GET.

```java
@GetMapping
```

### `@PostMapping`

Procesa solicitudes HTTP POST.

```java
@PostMapping
```

### `@PutMapping`

Procesa solicitudes HTTP PUT.

```java
@PutMapping("/{id}")
```

### `@DeleteMapping`

Procesa solicitudes HTTP DELETE.

```java
@DeleteMapping("/{id}")
```

### `@PathVariable`

Obtiene un valor directamente desde la URL.

```java
@PathVariable Long id
```

Ejemplo:

```text
/api/productos/1
```

El valor:

```text
1
```

se recibe mediante:

```java
@PathVariable Long id
```

### `@RequestBody`

Permite recibir información JSON enviada en el cuerpo de una solicitud.

```java
@RequestBody Producto producto
```

---

# 21. Arquitectura básica

El proyecto utiliza una estructura sencilla:

```text
                 ┌───────────────┐
                 │    Postman    │
                 └───────┬───────┘
                         │
                         │ HTTP
                         ▼
              ┌─────────────────────┐
              │   Spring Boot API   │
              │                     │
              │ /api/productos      │
              └──────────┬──────────┘
                         │
                         ▼
              ┌─────────────────────┐
              │ ProductoController  │
              └──────────┬──────────┘
                         │
                         ▼
              ┌─────────────────────┐
              │  List<Producto>     │
              │    ArrayList        │
              └─────────────────────┘
```

---

# 22. Ejemplo completo de JSON

Producto:

```json
{
    "id": 1,
    "nombre": "Laptop",
    "precio": 2500000
}
```

Nuevo producto:

```json
{
    "nombre": "Monitor",
    "precio": 850000
}
```

Actualización:

```json
{
    "nombre": "Laptop Lenovo",
    "precio": 2800000
}
```

---

# 23. Ejecutar desde Maven

También se puede ejecutar desde una terminal ubicada en la raíz del proyecto.

```bash
mvn spring-boot:run
```

O generar el archivo JAR:

```bash
mvn clean package
```

Posteriormente:

```bash
java -jar target/rest-productos-0.0.1-SNAPSHOT.jar
```

---

# 24. Verificación rápida

Una vez ejecutada la aplicación, abrir Postman y realizar:

```text
GET
http://localhost:8080/api/productos
```

Si todo está correctamente configurado, se visualizarán los productos:

```json
[
    {
        "id": 1,
        "nombre": "Laptop",
        "precio": 2500000.0
    },
    {
        "id": 2,
        "nombre": "Mouse",
        "precio": 80000.0
    },
    {
        "id": 3,
        "nombre": "Teclado",
        "precio": 120000.0
    }
]
```

---

# 25. Resultado final

El proyecto implementa una API REST básica utilizando:

```text
Java 21
    +
Spring Boot
    +
Spring Web
    +
Maven
    +
IntelliJ IDEA
    +
Postman
```

Con las operaciones:

```text
GET
POST
PUT
DELETE
```

sobre el recurso:

```text
/api/productos
```

---

## Autor

Proyecto educativo de práctica de desarrollo de servicios REST con Java y Spring Boot.
