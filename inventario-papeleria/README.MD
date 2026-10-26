
 Nombre: Gloria Punay.
Carnet: 9941-25-22033

---

# Sistema de Gestión de Inventario para Papelería (PF-13)

Este proyecto corresponde al primer avance del sistema backend para la gestión integral de inventario de una papelería. Está desarrollado con **Java 21**, **Spring Boot 3.2.5** y **PostgreSQL**, implementando una arquitectura por capas (MVC/Service) para garantizar el control de stock, procesamiento de movimientos y consultas estratégicas mediante una API REST.

---

##  Tabla de Contenidos
- [Descripción del Proyecto](#descripción-del-proyecto)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Modelo de Datos](#modelo-de-datos)
- [Reglas de Negocio Implementadas](#reglas-de-negocio-implementadas)
- [Endpoints de la API REST](#endpoints-de-la-api-rest)
- [Instrucciones de Ejecución](#instrucciones-de-ejecución)

---

##  Descripción del Proyecto

Las pequeñas y medianas empresas del sector de la papelería suelen enfrentarse a graves ineficiencias operativas debido a la falta de un sistema automatizado para el control de sus existencias. La gestión tradicional del inventario provocaba discrepancias constantes entre el stock físico real y el registrado, derivando en desabastecimiento de productos clave e incapacidad de evaluar el valor del negocio en tiempo real.

Este sistema centraliza la administración de categorías, productos y movimientos de inventario, permitiendo consultar el stock crítico y calcular el valor total acumulado mediante endpoints expuestos y validados.

---

##  Tecnologías Utilizadas

- **Lenguaje de Programación:** Java 21
- **Framework Principal:** Spring Boot 3.2.5
    - Spring Data JPA (Persistencia)
    - Spring Web (API RESTful)
- **Base de Datos:** PostgreSQL
- **Gestor de Dependencias:** Maven
- **Servidor Embebido:** Apache Tomcat

---

##  Estructura del Proyecto

El código está organizado siguiendo la arquitectura por capas para separar las responsabilidades del sistema:

```text
src/main/java/com/papeleria/inventario/
├── model/                  # Entidades JPA (Categoría, Producto, MovimientoInventario, TipoMovimiento)
├── repository/             # Interfaces de acceso a datos (Spring Data JPA)
├── service/                # Lógica de negocio y validaciones
└── controller/             # Endpoints REST para la comunicación con el cliente
```

# Sistema de Gestión de Inventario - Papelería

Proyecto backend desarrollado en Java y Spring Boot para la gestión de productos e inventario mediante una arquitectura de API REST, validación de datos de entrada y persistencia en PostgreSQL.

---

## DTO, validaciones y manejo de errores

### Entidad utilizada
* **Producto (`Producto.java`):** Entidad de JPA mapeada a la tabla `productos` en PostgreSQL. Contiene las propiedades `id`, `codigo`, `nombre`, `descripcion`, `precio`, `stock`, `stockMinimo` y la relación `@ManyToOne` con la entidad `Categoria`.

### DTO creado
* **ProductoRequestDTO (`ProductoRequestDTO.java`):** Objeto de transferencia de datos utilizado para la recepción de información en los endpoints de creación (`POST`) y actualización (`PUT`), desacoplando el modelo de persistencia de la capa web.

### Validaciones implementadas
* **`@NotBlank`:** Aplicada en `codigo` y `nombre` para asegurar que no sean nulos ni contengan únicamente espacios en blanco.
* **`@Size(max = 150)`:** Limita la longitud máxima del campo `nombre` a 150 caracteres.
* **`@NotNull`:** Garantiza que `precio`, `stock` y `stockMinimo` sean obligatorios en la solicitud.
* **`@DecimalMin(value = "0.01")`:** Valida que el precio del producto sea un monto estrictamente mayor a cero.
* **`@Min(value = 0)`:** Asegura que los valores numéricos de `stock` y `stockMinimo` no sean negativos.

### Excepción personalizada
* **RecursoNoEncontradoException (`RecursoNoEncontradoException.java`):** Excepción de tiempo de ejecución (`RuntimeException`) que se dispara cuando un producto o categoría solicitada mediante un ID específico no existe en la base de datos. Es capturada por el manejador global `@RestControllerAdvice` para generar respuestas de error en formato JSON.

### Códigos HTTP utilizados
* **`200 OK`:** Retornado en consultas exitosas (`GET`) y actualizaciones de productos (`PUT`).
* **`201 Created`:** Retornado tras la creación exitosa de un nuevo producto (`POST`).
* **`204 No Content`:** Retornado al eliminar un producto existente (`DELETE`).
* **`400 Bad Request`:** Retornado cuando fallan las validaciones `@Valid` de Bean Validation en el DTO de entrada.
* **`404 Not Found`:** Retornado cuando no se encuentra el recurso solicitado en la base de datos.
* **`500 Internal Server Error`:** Retornado ante errores no controlados en el servidor.

---

## Forma de ejecutar y probar la API

### 1. Configuración de Base de Datos
Asegurarse de tener ejecutando PostgreSQL y contar con la base de datos `papeleria_db` configurada en el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/papeleria_db
spring.datasource.username=postgres
spring.datasource.password=tu_contraseña