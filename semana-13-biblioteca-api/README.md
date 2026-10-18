# Sistema de Gestión - Biblioteca Personal (API REST)

**Datos del Estudiante:**
* **Nombre:** GLORIA PUNAY
* **Carné:** 9941-25-22033

---

## 1. Contexto y Alcance del Proyecto
El presente proyecto abarca el diseño e implementación de una interfaz de programación de aplicaciones (API RESTful) enfocada en la administración centralizada de un catálogo bibliográfico denominado **"biblioteca_personal"**.

La solución integra el framework **Spring Boot** junto con el motor relacional **PostgreSQL**, garantizando el flujo de datos mediante un esquema de persistencia física permanente (sin recurrir a colecciones en memoria). La arquitectura responde de forma estricta al patrón MVC simplificado, abordando operaciones fundamentales de creación, lectura, modificación y borrado (CRUD), control eficiente de códigos de estado HTTP, restricciones de unicidad sobre la data y filtros específicos por estado y categoría.

---

## 2. Pila Tecnológica (Tech Stack)
* **Lenguaje de Programación:** Java (JDK 21 / 17+)
* **Framework Principal:** Spring Boot 3.x (Spring Web, Spring Data JPA)
* **Gestor de Base de Datos:** PostgreSQL
* **Cliente de Base de Datos:** pgAdmin 4
* **Pruebas de API e Integración:** Postman
* **Gestión de Dependencias:** Apache Maven
* **Entorno de Desarrollo Integrado (IDE):** IntelliJ IDEA

---

## 3. Arquitectura Modular del Proyecto
La solución se organiza internamente bajo una estructura multicapa limpia dentro del directorio raíz `src/main/java/com/tuproyecto/`:

```text
com.tuproyecto/
├── controller/
│   └── LibroController.java          <-- Exposición de Endpoints REST (@RestController)
├── model/
│   └── Libro.java                    <-- Mapeo Objeto-Relacional (@Entity)
├── repository/
│   └── LibroRepository.java          <-- Persistencia JPA (JpaRepository)
└── BibliotecaPersonalApplication.java <-- Punto de entrada de la aplicación
```

```text
CREATE DATABASE biblioteca_personal;

\c biblioteca_personal;

CREATE TABLE libro (
    id BIGSERIAL PRIMARY KEY,
    codigo VARCHAR(50) UNIQUE NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    anio_publicacion INT NOT NULL,
    precio NUMERIC(10, 2) NOT NULL,
    disponible BOOLEAN NOT NULL
);
```
## Parámetros de Conexión
spring.datasource.url=jdbc:postgresql://localhost:5432/biblioteca_personal
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect

## Catálogo de Servicios y Endpoints HTTP

| Método HTTP | Endpoint / Ruta | Propósito / Funcionalidad |
| :--- | :--- | :--- |
| **POST** | `/api/libros` | Inserción e inicialización de un nuevo registro bibliográfico. |
| **GET** | `/api/libros` | Extracción masiva del listado de libros almacenados. |
| **GET** | `/api/libros/{id}` | Búsqueda puntual de un recurso mediante su identificador numérico. |
| **GET** | `/api/libros/categoria/{categoria}` | Filtrado dinámico de libros según su categoría. |
| **GET** | `/api/libros/disponibles` | Consulta especializada para listar libros en estado disponible (`true`). |
| **PUT** | `/api/libros/{id}` | Modificación y actualización de atributos de un registro existente. |
| **DELETE** | `/api/libros/{id}` | Remoción física del registro en la base de datos por ID. |