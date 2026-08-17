
**Nombre:** Gloria Esperanza Punay Xocoxic  
**Carné:** 9941-25-25-22033
---

Para el programa de cobros en un parqueo, se solicita la cantidad de vehículos a procesar y, mediante un ciclo `for`, pide los datos de cada uno (placa, tipo, horarios y estado del ticket) aplicando validaciones de entrada. Calcula la tarifa, aplica un 15% de descuento si pasa de 8 horas y cobra Q50 si se perdió el ticket. Al final imprime el comprobante de cada carro y un resumen general con las recaudaciones del día.

---

## Métodos creados y su función

* `obtenerTarifa(int tipoVehiculo)`: Retorna la tarifa por hora (Q5.00 moto, Q8.00 auto, Q12.00 pickup).
* `obtenerNombreVehiculo(int tipoVehiculo)`: Convierte la opción del tipo de vehículo a texto.
* `calcularDescuento(double subtotal, int horas)`: Aplica el 15% de descuento si las horas superan las 8 horas.
* `calcularPago(int horas, double tarifa)`: Calcula el total a pagar cuando el usuario **sí** tiene ticket.
* `calcularPago(int horas, double tarifa, double recargo)`: Calcula el total cuando el usuario **perdió** el ticket (suma los Q50).
* `mostrarComprobante(...)`: Imprime el comprobante individual del vehículo.
* `mostrarResumenJornada(...)`: Imprime las estadísticas finales y totales recaudados.
* `pedirNumero(...)`: Método auxiliar para validar los rangos de horas (0-23) y minutos (0-59).
* `calcularMinutosTotales(...)`: Saca la diferencia en minutos y maneja el cambio de día (pasada la medianoche).
* `calcularHorasCobro(...)`: Redondea cualquier fracción de minutos hacia la siguiente hora completa.

---

## Explicación de la sobrecarga

Apliqué la sobrecarga en el método `calcularPago` variando la cantidad de parámetros:
* `calcularPago(int, double)`: Se llama cuando no se perdió el ticket (2 parámetros).
* `calcularPago(int, double, double)`: Se llama cuando sí se perdió el ticket (3 parámetros), sumando los Q50 del recargo.

---

## Casos de prueba utilizados

1. **Normal:** Automóvil, 3 horas, sin perder ticket. Calculó Q24.00 exactos.
2. **Descuento y recargo:** Motocicleta, 10 horas y ticket perdido. Aplicó 15% de descuento sobre el subtotal y sumó Q50 de recargo.
3. **Cruce de medianoche:** Entrada 22:30 y salida 02:10 (3h 40m). Cobró 4 horas completas.
4. **Validaciones:** Se ingresaron letras, horas inválidas (25) y opciones fuera de rango. El programa rechazó el dato y lo volvió a pedir.

---

## Reto opcional

El programa pide horas y minutos (0-23 y 0-59), calcula bien el tiempo cuando la salida es al día siguiente y cobra la fracción de tiempo como una hora completa.













