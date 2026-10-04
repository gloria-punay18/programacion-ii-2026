
* **Nombre Completo:** Gloria Punay
* **Número de Carné:** 9941-25-22033
---


# Actividad Práctica: API REST para Control de Despensa Doméstica

## 1. Descripción del Problema
El control manual de una despensa doméstica es ineficiente y propenso a errores, lo que genera desabastecimiento o pérdida de productos. Para resolverlo, se requiere desarrollar una API REST en Spring Boot que permita gestionar el inventario en memoria y consultar métricas clave como existencias, productos con bajo stock, ítem de mayor valor y resúmenes consolidados.

## 2. Tecnologías Utilizadas
* **Lenguaje:** Java 17
* **Framework:** Spring Boot (Spring Web)
* **Herramientas:** Maven, IntelliJ IDEA, Apache Tomcat (Puerto 8080)
* **Formato:** JSON

---

## 3. Requisitos de Ejecución
* JDK 17 o superior.
* Apache Maven (o `./mvnw`).
* Navegador Web o cliente HTTP (Postman).

---

## 4. Estructura Principal
```text
control-despensa-api/
 └── src/main/java/com/estudiante/despensa/
      ├── ControlDespensaApiApplication.java
      ├── controller/
      │    └── ProductoController.java
      └── model/
           ├── Producto.java
           └── ResumenInventario.java
 ```      
---
## 5. Explicación de las Clases
* **`Producto.java`:** Modelo que representa los ítems del inventario y calcula su subtotal.
* **`ResumenInventario.java`:** DTO que consolida las métricas del inventario (tipos de productos, unidades y valor total).
* **`ProductoController.java`:** Controlador REST con los endpoints para consultar y filtrar el inventario en memoria.
* **`ControlDespensaApiApplication.java`:** Clase principal que inicia Spring Boot y el servidor Tomcat.

## 6. Tabla de endpoints.
![img.png](../../../Desktop/programacion-ii-2026/control-despensa-api/control-despensa-api/img.png)
## 7. Instrucciones para Ejecutar la Aplicación

1. **Abrir el proyecto** en IntelliJ IDEA.
2. **Cargar dependencias** de Maven automáticamente.
3. **Ejecutar la aplicación**:
    * Desde el IDE: Ejecutar la clase `ControlDespensaApiApplication.java`.
    * Desde la terminal: Ejecutar `./mvnw spring-boot:run`.
4. **Verificar ejecución**: Confirmar en la consola que el servidor Tomcat se inició en el puerto `8080`.


## 8. Ejemplos de Respuestas JSON

### Consultar Todos los Productos (`GET /api/productos`)
```json
[
  {
    "id": 1,
    "nombre": "Leche Entera",
    "categoria": "Lácteos",
    "cantidad": 5,
    "precioUnitario": 2.5
  },
  {
    "id": 2,
    "nombre": "Queso Fresco",
    "categoria": "Lácteos",
    "cantidad": 2,
    "precioUnitario": 4.0
  }
]
```
### Consultar Resumen del Inventario (GET /api/productos/resumen)
```json

{
  "cantidadProductos": 6,
  "totalUnidades": 29,
  "valorTotal": 69.7
}
```
### Producto de Mayor Valor (GET /api/productos/mayor-valor)
```json
{
  "id": 3,
  "nombre": "Arroz Gallo Dorado",
  "categoria": "Granos",
  "cantidad": 10,
  "precioUnitario": 1.8
}
```



