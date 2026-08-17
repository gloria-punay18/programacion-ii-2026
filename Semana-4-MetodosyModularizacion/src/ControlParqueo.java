import java.util.Scanner;

public class ControlParqueo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nombre: Gloria Esperanza Punay Xocoxic");
        System.out.println("Carne: 9941-25-22033");


        System.out.println("==========================================");
        System.out.println("   SISTEMA DE CONTROL DE PARQUEO");
        System.out.println("==========================================");


        int cantidadVehiculos = 0;
        while (cantidadVehiculos <= 0) {
            System.out.print("Ingrese la cantidad de vehiculos a registrar: ");
            if (scanner.hasNextInt()) {
                cantidadVehiculos = scanner.nextInt();
                if (cantidadVehiculos <= 0) {
                    System.out.println("Error: Debe ingresar un numero mayor a cero.\n");
                }
            } else {
                System.out.println("Error: Entrada invalida. Ingrese un numero entero.\n");
                scanner.next();
            }
        }


        int cantMotocicletas = 0;
        int cantAutomoviles = 0;
        int cantPickups = 0;
        int cantTicketsPerdidos = 0;
        double totalRecaudado = 0.0;

        double pagoMaximo = -1.0;
        String placaPagoMaximo = "";
        String tipoPagoMaximo = "";

        for (int i = 1; i <= cantidadVehiculos; i++) {
            System.out.println("\n------------------------------------------");
            System.out.println("REGISTRO DE VEHICULO #" + i);
            System.out.println("------------------------------------------");

            System.out.print("Ingrese el numero de placa: ");
            String placa = scanner.next().toUpperCase();


            int tipoVehiculo = 0;
            while (tipoVehiculo < 1 || tipoVehiculo > 3) {
                System.out.println("Tipo de vehiculo: (1: Motocicleta, 2: Automovil, 3: Pickup/Camioneta)");
                System.out.print("Seleccione una opcion (1-3): ");
                if (scanner.hasNextInt()) {
                    tipoVehiculo = scanner.nextInt();
                    if (tipoVehiculo < 1 || tipoVehiculo > 3) {
                        System.out.println("Error: Opcion fuera de rango. Intente de nuevo.\n");
                    }
                } else {
                    System.out.println("Error: Debe ingresar un numero (1, 2 o 3).\n");
                    scanner.next();
                }
            }


            int horaEntrada = solicitarValorValido(scanner, "Hora de entrada (0-23): ", 0, 23);
            int minutoEntrada = solicitarValorValido(scanner, "Minuto de entrada (0-59): ", 0, 59);
            int horaSalida = solicitarValorValido(scanner, "Hora de salida (0-23): ", 0, 23);
            int minutoSalida = solicitarValorValido(scanner, "Minuto de salida (0-59): ", 0, 59);


            int minutosTranscurridos = calcularMinutosTotales(horaEntrada, minutoEntrada, horaSalida, minutoSalida);
            int horasCobrar = calcularHorasCobro(minutosTranscurridos);

            int horasImprimibles = minutosTranscurridos / 60;
            int minutosRestantes = minutosTranscurridos % 60;


            char ticketPerdido = ' ';
            while (ticketPerdido != 'S' && ticketPerdido != 'N') {
                System.out.print("¿Perdio el ticket? (S/N): ");
                String input = scanner.next().toUpperCase();
                if (input.length() == 1 && (input.charAt(0) == 'S' || input.charAt(0) == 'N')) {
                    ticketPerdido = input.charAt(0);
                } else {
                    System.out.println("Error: Ingrese unicamente 'S' para Si o 'N' para No.\n");
                }
            }

            double tarifa = obtenerTarifa(tipoVehiculo);
            String nombreTipo = obtenerNombreVehiculo(tipoVehiculo);
            double subtotal = horasCobrar * tarifa;
            double descuento = calcularDescuento(subtotal, horasCobrar);
            double recargo = (ticketPerdido == 'S') ? 50.0 : 0.0;


            double totalPagar;
            if (recargo > 0) {
                totalPagar = calcularPago(horasCobrar, tarifa, recargo);
            } else {
                totalPagar = calcularPago(horasCobrar, tarifa);
            }


            mostrarComprobante(placa, nombreTipo, horasCobrar, tarifa, subtotal, descuento, recargo, totalPagar, horasImprimibles, minutosRestantes);


            switch (tipoVehiculo) {
                case 1: cantMotocicletas++; break;
                case 2: cantAutomoviles++; break;
                case 3: cantPickups++; break;
            }

            if (ticketPerdido == 'S') {
                cantTicketsPerdidos++;
            }

            totalRecaudado += totalPagar;


            if (totalPagar > pagoMaximo) {
                pagoMaximo = totalPagar;
                placaPagoMaximo = placa;
                tipoPagoMaximo = nombreTipo;
            }
        }


        mostrarResumenJornada(cantMotocicletas, cantAutomoviles, cantPickups, cantTicketsPerdidos, totalRecaudado, placaPagoMaximo, tipoPagoMaximo, pagoMaximo);

        scanner.close();
    }


    public static double obtenerTarifa(int tipoVehiculo) {
        switch (tipoVehiculo) {
            case 1: return 5.0;  // Motocicleta
            case 2: return 8.0;  // Automovil
            case 3: return 12.0; // Pickup / Camioneta
            default: return 0.0;
        }
    }


    public static String obtenerNombreVehiculo(int tipoVehiculo) {
        switch (tipoVehiculo) {
            case 1: return "Motocicleta";
            case 2: return "Automovil";
            case 3: return "Pickup o Camioneta";
            default: return "Desconocido";
        }
    }


    public static double calcularDescuento(double subtotal, int horas) {
        if (horas > 8) {
            return subtotal * 0.15;
        }
        return 0.0;
    }




    public static double calcularPago(int horas, double tarifa) {
        double subtotal = horas * tarifa;
        double descuento = calcularDescuento(subtotal, horas);
        return subtotal - descuento;
    }

    public static double calcularPago(int horas, double tarifa, double recargo) {
        return calcularPago(horas, tarifa) + recargo;
    }

    public static void mostrarComprobante(String placa, String tipo, int horasCobro, double tarifa,
                                          double subtotal, double descuento, double recargo, double total,
                                          int horasReales, int minutosReales) {

        System.out.println("\n========== COMPROBANTE ==========");
        System.out.println("Placa: " + placa);
        System.out.println("Tipo: " + tipo);
        System.out.println("Tiempo estacionado: " + horasReales + " horas y " + minutosReales + " minutos");
        System.out.println("Horas cobradas: " + horasCobro);
        System.out.printf("Tarifa por hora: Q%.2f\n", tarifa);
        System.out.printf("Subtotal: Q%.2f\n", subtotal);
        System.out.printf("Descuento: Q%.2f\n", descuento);
        System.out.printf("Recargo por ticket perdido: Q%.2f\n", recargo);
        System.out.printf("TOTAL: Q%.2f\n", total);
        System.out.println("=================================");
    }


    public static void mostrarResumenJornada(int motos, int autos, int pickups, int ticketsPerdidos,
                                             double totalDinero, String placaMax, String tipoMax, double pagoMax) {
        System.out.println("\n==========================================");
        System.out.println("          RESUMEN DE LA JORNADA");
        System.out.println("==========================================");
        System.out.println("Cantidad de motocicletas: " + motos);
        System.out.println("Cantidad de automoviles: " + autos);
        System.out.println("Cantidad de pickups/camionetas: " + pickups);
        System.out.println("Total de tickets perdidos: " + ticketsPerdidos);
        System.out.printf("Total de dinero recaudado: Q%.2f\n", totalDinero);
        if (pagoMax > -1.0) {
            System.out.printf("Vehiculo con mayor pago: Placa %s (%s) con un total de Q%.2f\n", placaMax, tipoMax, pagoMax);
        }
        System.out.println("==========================================");
    }


    public static int solicitarValorValido(Scanner scanner, String mensaje, int min, int max) {
        int valor = -1;
        while (valor < min || valor > max) {
            System.out.print(mensaje);
            if (scanner.hasNextInt()) {
                valor = scanner.nextInt();
                if (valor < min || valor > max) {
                    System.out.printf("Error: Ingrese un valor entre %d y %d.\n", min, max);
                }
            } else {
                System.out.println("Error: Debe ingresar un numero valido.");
                scanner.next();
            }
        }
        return valor;
    }


    public static int calcularMinutosTotales(int hEntrada, int mEntrada, int hSalida, int mSalida) {
        int inicioMinutos = (hEntrada * 60) + mEntrada;
        int finMinutos = (hSalida * 60) + mSalida;


        if (finMinutos < inicioMinutos) {
            finMinutos += (24 * 60);
        }

        return finMinutos - inicioMinutos;
    }


    public static int calcularHorasCobro(int minutosTotales) {
        if (minutosTotales <= 0) return 1;
        return (int) Math.ceil((double) minutosTotales / 60.0);
    }
}
