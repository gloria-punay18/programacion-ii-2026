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


# Sistema de Gestión de Libros - Biblioteca Personal

Este proyecto consiste en una aplicación web completa para la gestión de una biblioteca personal, desarrollada con una arquitectura orientada a servicios (API REST) en el backend y una interfaz dinámica e interactiva en el frontend.

---

## Interfaz web

### Dirección de acceso
Para acceder a la aplicación web desde el navegador, utiliza la siguiente URL cuando el servidor esté en ejecución:
* **`http://localhost:8080`** (o directamente la ruta principal configurada en el servidor local).

### Tecnologías utilizadas
* **HTML5:** Estructuración semántica de la interfaz y validaciones nativas de formulario (`required`).
* **CSS3 & Bootstrap 5:** Diseño adaptativo (responsive), maquetación con Grid/Flexbox y componentes estilizados (paneles, tablas, botones y banners de alerta).
* **JavaScript (ES6+):** Lógica del cliente, manipulación dinámica del DOM y consumo asíncrono de servicios mediante `fetch()` y procesamiento de objetos JSON.

### Funcionalidades disponibles
* **Consulta de libros:** Carga y visualización dinámica en una tabla de todos los registros almacenados en la base de datos al ingresar a la página.
* **Registro de nuevos libros:** Formulario para agregar libros indicando código, título, autor, categoría, año de publicación, precio y estado de disponibilidad.
* **Validación de campos obligatorios:** Control de entrada en tiempo real con alertas del navegador antes del envío.
* **Control de estado (Disponible / No disponible):** Visualización clara del estado mediante etiquetas de colores personalizados.
* **Eliminación de registros con confirmación:** Cuadro de diálogo interactivo para solicitar confirmación del usuario antes de borrar permanentemente un libro de la base de datos.
* **Manejo de errores e inconsistencias:** Banners informativos de éxito (verde) y de error (rojo) para notificar el resultado de las operaciones (como intentos de registro con código duplicado).

### Endpoints consumidos
La interfaz consume la API REST del backend mediante las siguientes rutas HTTP:

| Método HTTP | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/libros` | Recupera la lista completa de libros almacenados en la base de datos. |
| **POST** | `/api/libros` | Envía en el cuerpo de la petición (JSON) los datos para registrar un nuevo libro. |
| **DELETE** | `/api/libros/{id}` | Elimina de la base de datos el libro correspondiente al identificador (`id`) especificado. |

### Instrucciones para ejecutar el sistema

1. **Prerrequisitos:**
    * Tener instalado **Java JDK 17+** y **Maven**.
    * Tener instalado **PostgreSQL** y un gestor como **pgAdmin**.

2. **Configuración de la Base de Datos:**
    * Crear la base de datos llamada `biblioteca_personal` en PostgreSQL.
    * Verificar la configuración de conexión en el archivo `src/main/resources/application.properties` (usuario, contraseña y puerto).

3. **Ejecución del Backend:**
    * Abrir la terminal en la raíz del proyecto y ejecutar:
      ```bash
      mvn spring-boot:run
      ```
    * O bien, ejecutar la clase principal desde el IDE de preferencia (IntelliJ IDEA, Eclipse, VS Code).

4. **Acceso a la Interfaz:**
    * Una vez iniciada la aplicación Spring Boot sin errores, abrir el navegador e ingresar a:
      ```text
      http://localhost:8080
      ```

### Captura de la página terminada
*(Pega aquí la imagen de tu interfaz web funcionando con la tabla y el formulario)*




