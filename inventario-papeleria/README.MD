#  PF-13 | Gestión de Inventario para Papelería

---

##  Datos del Estudiante
- **Nombre Completo:** Gloria Punay
- **Número de Carné:** 9941-25-22033
- **Curso:** Programación II
- **Universidad:** Universidad Mariano Gálvez de Guatemala

---

## Descripción del Problema
Las tiendas de papelería suelen manejar un catálogo extenso de productos con constantes entradas por abastecimiento y salidas por ventas diarias. La falta de un sistema centralizado genera problemas como:
- Pérdida de control del stock disponible y descuadres entre inventario físico y registros.
- Ausencia de alertas oportunas cuando un producto alcanza niveles críticos (stock mínimo).
- Falta de trazabilidad en los movimientos de inventario (quién, cuándo y cuánta mercancía entra o sale).
- Modificaciones arbitrarias del stock que vulneran la integridad de los datos.

**Solución Desarrollada:**  
Un sistema de gestión de inventario robusto y web que automatiza el control de existencias, restringe las modificaciones directas de stock obligando a registrar movimientos de **ENTRADA** o **SALIDA**, emite alertas de stock bajo en tiempo real, genera reportes ejecutivos (KPIs y exportación CSV) y ofrece una interfaz moderna de alto rendimiento con tema **Dark Mode Neon**.

---

## Tecnologías Utilizadas

### Backend
- **Lenguaje:** Java 17
- **Framework:** Spring Boot 3.x (Spring Web, Spring Data JPA)
- **Gestor de Dependencias:** Apache Maven

### Base de Datos
- **Motor BD:** PostgreSQL 14+
- **ORM:** Hibernate / JPA

### Frontend
- **Lenguajes:** HTML5, CSS3, JavaScript (ES6+ / Vanilla JS)
- **Diseño & Estilos:** Modern Dark Neon (Glassmorphism, CSS Grid, Flexbox, Keyframe Animations, SVG)
- **Comunicación HTTP:** Fetch API (Asíncrono)

---

## Estructura General del Sistema

```text
PF13-Inventario-Papelaria/
├── src/
│   ├── main/
│   │   ├── java/com/papeleria/
│   │   │   ├── controllers/         # Endpoints REST API (ProductoController, MovimientoController)
│   │   │   ├── models/              # Entidades JPA (Producto, Movimiento, Categoria)
│   │   │   ├── repositories/        # Interfaces Spring Data JPA (ProductoRepository, MovimientoRepository)
│   │   │   ├── services/            # Lógica de Negocio y Reglas de Integridad (ProductoService, MovimientoService)
│   │   │   └── PapeleriaApplication.java # Clase Principal Spring Boot
│   │   └── resources/
│   │       ├── static/              # Frontend Web
│   │       │   ├── index.html       # Estructura del Dashboard y Formularios
│   │       │   ├── styles.css       # Estilos Dark Neon y Animaciones
│   │       │   └── app.js           # Lógica interactiva, consumo de APIs y KPIs
│   │       └── application.properties # Configuración de PostgreSQL y Spring
└── README.md                        # Documentación del Proyecto
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
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```
## Requisitos para Ejecutar el Proyecto

- **Java Development Kit (JDK):** Versión 17 o superior.
- **PostgreSQL:** Versión 14 o superior ejecutándose en el puerto `5432`.
- **Navegador Web:** Chrome, Firefox, Edge o cualquier navegador moderno con soporte para ES6+.
- **Herramienta de Construcción:** Maven (incluido mediante `./mvnw` en el proyecto).
- **IDE Recomendado (Opcional):** IntelliJ IDEA, VS Code o Eclipse.

## Instrucciones para Iniciar el Backend

1. Abre una terminal dentro del directorio raíz del proyecto.
2. Compila y ejecuta la aplicación Spring Boot utilizando Maven:
    - **En Windows:**
      ```bash
      mvnw.cmd spring-boot:run
      ```
    - **En Linux / macOS:**
      ```bash
      ./mvnw spring-boot:run
      ```
3. El servidor backend iniciará correctamente y quedará a la escucha en: `http://localhost:8080/`

##  Instrucciones para Abrir el Frontend

Dado que el frontend está integrado como recurso estático dentro de Spring Boot en el directorio (`src/main/resources/static/`), no requiere instalar servidores de desarrollo adicionales (como Nginx o Node.js).

Simplemente abre tu navegador web de preferencia e ingresa a la siguiente dirección:

👉 **`http://localhost:8080/`**

La interfaz **Dark Mode Neon** cargará automáticamente consumiendo la API REST local.

## Descripción de los Endpoints (REST API)

### 🔹 Módulo de Productos (`/api/productos`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **GET** | `/api/productos` | Obtiene el catálogo completo de productos registrados. |
| **GET** | `/api/productos/{id}` | Obtiene los detalles de un producto específico por su ID. |
| **POST** | `/api/productos` | Crea y guarda un nuevo producto en el catálogo. |
| **PUT** | `/api/productos/{id}` | Actualiza los datos de un producto (código, nombre, precio, stock mínimo). |
| **DELETE** | `/api/productos/{id}` | Elimina un producto del catálogo (sujeto a reglas de integridad). |

### 🔹 Módulo de Movimientos (`/api/movimientos`)

| Método | Endpoint | Descripción |
| :--- | :--- | :--- |
| **POST** | `/api/movimientos` | Registra un movimiento de **ENTRADA** o **SALIDA**, actualizando automáticamente el stock del producto. |
| **GET** | `/api/movimientos` | Consulta el historial completo de movimientos registrados para auditoría y trazabilidad. |


## Reglas Principales del Negocio

1. **Preservación del Stock en Edición (`ProductoService`):**  
   Al editar la información básica de un producto (mediante `PUT /api/productos/{id}`), el campo `stock` **permanece inalterado** en la base de datos para prevenir manipulaciones arbitrarias.

2. **Control Estricto de Trazabilidad (`MovimientoService`):**  
   La única forma válida de alterar las existencias físicas de un producto es mediante el registro formal de un **Movimiento de Inventario**:
    - **ENTRADA:** Incrementa las existencias según la cantidad indicada.
    - **SALIDA:** Decrementa las existencias según la cantidad indicada.

3. **Validación de Stock Insuficiente:**  
   No se permite registrar un movimiento de tipo **SALIDA** si la cantidad solicitada supera el stock actual del producto. El sistema rechazará la operación devolviendo un código de error de negocio.

4. **Alertas Automáticas de Stock Bajo:**  
   Si las existencias actuales de un producto son menores o iguales al `stockMinimo` configurado, la interfaz lo marcará con la etiqueta **"¡Bajo!"** y lo contabilizará en el panel de alertas.

5. **Restricción de Eliminación Integrada:**  
   No es posible eliminar productos que tengan historial de movimientos registrados, protegiendo la integridad referencial de los datos administrativos.

   