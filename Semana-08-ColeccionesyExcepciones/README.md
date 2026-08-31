# 👥 Integrantes del Equipo

* **Gloria Esperanza Punay Xocoxic** | Carné: 9941-25-22033
* **Jussely Saraí Mutas Chitic** | Carné: 9941-25-28221
* **Kevin Oswaldo Rivera Sánchez** | Carné: 9941-25-612

---

# Sistema Veterinario VetCare — Semana 8: Colecciones y Excepciones

Este proyecto es una aplicación desarrollada en Java para gestionar el registro de pacientes en una clínica veterinaria. Implementa el paradigma de Programación Orientada a Objetos (POO), colecciones dinámicas (`ArrayList`) y manejo de excepciones mediante `try-catch`.

---

## Descripción de la Solución (Caso A)

Para esta actividad se seleccionó el **Caso A**, utilizando la colección `ArrayList<Mascota>` debido a sus ventajas para mantener el orden secuencial de registro de los pacientes y permitir la manipulación dinámica de los datos.

### Requerimientos Cumplidos:
1. **Registro de datos:** Se ingresan tres mascotas utilizando el método `.add()`.
2. **Conteo dinámico:** Se consulta y muestra la cantidad actual de pacientes mediante `.size()`.
3. **Recorrido de lista:** Se implementa un ciclo `for-each` para mostrar la información, sonido característico y costo de consulta de cada tipo de mascota (demostrando polimorfismo).
4. **Eliminación:** Se elimina la primera mascota registrada mediante el método `.remove(0)`.
5. **Control de Entradas (Excepciones):** Se captura la excepción `NumberFormatException` cuando el usuario ingresa letras en lugar de números en la consola, mostrando un mensaje de advertencia sin interrumpir el programa.

---




