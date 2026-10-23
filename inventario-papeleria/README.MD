
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