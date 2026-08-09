#  Cajero Automático


**Nombre:** Gloria Esperanza Punay Xocoxic  
**Carné:** 9941-25-22033

---

## Descripción del Proyecto

Este programa es una simulación de consola en Java que recrea las operaciones esenciales de un **Cajero Automático**. Fue desarrollado con un enfoque modular, aplicando buenas prácticas de programación y cumpliendo con la integración de todos los temas evaluados en el primer parcial.

## Credenciales de Acceso (Para Pruebas)

Para poder ingresar al sistema y utilizar las funciones del cajero automático, utiliza el siguiente PIN:

* **PIN de Acceso:** `010203`

* Tendrá un Saldo Inicial de:** `Q10,000.00`
---

## Conceptos Integrados en el Código

El programa evidencia el uso práctico de los siguientes temas estudiados:

* **Variables y Tipos de Datos:** Uso de `double`, `int`, `String` y variables de estado `static`.
* **Operadores:** Operadores aritméticos (`+=`, `-=`, `*`), relacionales (`!=`, `<=`, `>`) y lógicos (`&&`, `!`).
* **Entrada de Datos:** Captura dinámica mediante la clase `Scanner`.
* **Estructuras de Control Condicional:** Control de acceso mediante `if/else` y menú de opciones con `switch`.
* **Ciclos de Repetición:** Ciclo `while` para la navegación continua dentro de la aplicación.
* **Control del Flujo:** Uso de `break` para salir del selector y `return` en validaciones críticas, como el PIN.
* **Modularización y Métodos:** Separación de responsabilidades en métodos específicos para menú, saldos, retiros, depósitos e impresión.
* **Valores de Retorno y Parámetros:** Paso de parámetros por valor y métodos que devuelven resultados tipo `boolean` y `double`.

---

## Sobrecarga de Métodos 

El requerimiento principal de la evaluación se cumple mediante la implementación de métodos sobrecargados (mismo nombre, distinta lista de parámetros):

### 1. Métodos de Retiro 
* `retirar(double monto)` $\rightarrow$ Realiza un retiro directo en la cuenta por defecto.
* `retirar(double monto, String tipoCuenta)` $\rightarrow$ Permite especificar el tipo de cuenta (e.g., *Ahorros* o *Monetaria*).

### 2. Métodos de Depósito 
* `depositar(double monto)` $\rightarrow$ Acredita un monto global directamente al saldo.
* `depositar(int cantidadBilletes, int denominacion)` $\rightarrow$ Calcula el total a depositar multiplicando la cantidad de billetes por su denominación (Q20, Q50, Q100, Q200).

---
### Ver evidencias de ejecución









# [https://github.com/TU-USUARIO/NOMBRE-DE-TU-REPOSITORIO.git](https://github.com/TU-USUARIO/NOMBRE-DE-TU-REPOSITORIO.git)