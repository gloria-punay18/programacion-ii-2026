import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static ArrayList<Vehiculo> listaVehiculos = new ArrayList<>();
    private static HashSet<String> placasRegistradas = new HashSet<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        do {
            try {
                System.out.println("\n=== SISTEMA DE GESTIÓN DE ESTACIONAMIENTO ===");
                System.out.println("1. Registrar vehículo");
                System.out.println("2. Mostrar todos los vehículos registrados");
                System.out.println("3. Buscar vehículo por placa");
                System.out.println("4. Mostrar vehículo con mayor costo");
                System.out.println("5. Mostrar total general recaudado");
                System.out.println("6. Mostrar total recaudado por tipo de vehículo");
                System.out.println("7. Salir");
                System.out.print("Seleccione una opción: ");

                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        registrarVehiculo();
                        break;
                    case 2:
                        mostrarVehiculos();
                        break;
                    case 3:
                        buscarPorPlaca();
                        break;
                    case 4:
                        mostrarMayorCosto();
                        break;
                    case 5:
                        mostrarTotalGeneral();
                        break;
                    case 6:
                        mostrarTotalesPorTipo();
                        break;
                    case 7:
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {

                System.out.println("Error de entrada: Debe ingresar un número entero válido.");
                scanner.nextLine();
            } finally {

                System.out.println("[Operación finalizada]");
            }
        } while (opcion != 7);

        scanner.close();
    }


    private static void registrarVehiculo() {
        System.out.println("\n--- REGISTRO DE VEHÍCULO ---");

        System.out.print("Ingrese el tipo (1. Automóvil / 2. Motocicleta): ");
        int tipo = 0;
        try {
            tipo = scanner.nextInt();
            scanner.nextLine();
            if (tipo != 1 && tipo != 2) {
                System.out.println("Error: Tipo de vehículo inválido.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error: Debe ingresar un número (1 o 2).");
            scanner.nextLine();
            return;
        }

        System.out.print("Ingrese la placa: ");
        String placa = scanner.nextLine().trim().toUpperCase();
        if (placa.isEmpty()) {
            System.out.println("Error: La placa no puede estar vacía.");
            return;
        }


        if (placasRegistradas.contains(placa)) {
            System.out.println("Error: La placa " + placa + " ya se encuentra registrada en el sistema.");
            return;
        }

        System.out.print("Ingrese el nombre del propietario: ");
        String propietario = scanner.nextLine().trim();
        if (propietario.isEmpty()) {
            System.out.println("Error: El propietario no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese la hora de ingreso (ej. 08:30): ");
        String horaIngreso = scanner.nextLine().trim();
        if (horaIngreso.isEmpty()) {
            System.out.println("Error: La hora de ingreso no puede estar vacía.");
            return;
        }

        double horas = 0;
        System.out.print("Ingrese las horas utilizadas: ");
        try {
            horas = scanner.nextDouble();
            scanner.nextLine();
            if (horas <= 0) {
                System.out.println("Error: Las horas utilizadas deben ser mayores a 0.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Error de entrada: Debe ingresar un valor numérico para las horas.");
            scanner.nextLine();
            return;
        }


        Vehiculo nuevoVehiculo;
        if (tipo == 1) {
            nuevoVehiculo = new Automovil(placa, propietario, horaIngreso, horas);
        } else {
            nuevoVehiculo = new Motocicleta(placa, propietario, horaIngreso, horas);
        }


        listaVehiculos.add(nuevoVehiculo);
        placasRegistradas.add(placa);

        System.out.println("¡Vehículo registrado exitosamente!");
    }
    private static void mostrarVehiculos() {
        System.out.println("\n--- LISTADO DE VEHÍCULOS REGISTRADOS ---");
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados en el sistema.");
            return;
        }
        for (Vehiculo v : listaVehiculos) {
            v.mostrarInformacion();
        }
    }

    private static void buscarPorPlaca() {
        System.out.println("\n--- BÚSQUEDA POR PLACA ---");
        System.out.print("Ingrese la placa a buscar: ");
        String placa = scanner.nextLine().trim().toUpperCase();

        for (Vehiculo v : listaVehiculos) {
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                System.out.println("Vehículo encontrado:");
                v.mostrarInformacion();
                return;
            }
        }
        System.out.println("No se encontró ningún vehículo registrado con la placa: " + placa);
    }

    private static void mostrarMayorCosto() {
        System.out.println("\n--- VEHÍCULO CON MAYOR COSTO ---");
        if (listaVehiculos.isEmpty()) {
            System.out.println("No hay vehículos registrados.");
            return;
        }

        Vehiculo mayor = listaVehiculos.get(0);
        for (Vehiculo v : listaVehiculos) {
            if (v.calcularCosto() > mayor.calcularCosto()) {
                mayor = v;
            }
        }
        System.out.println("El vehículo que generó el mayor costo es:");
        mayor.mostrarInformacion();
    }

    private static void mostrarTotalGeneral() {
        System.out.println("\n--- TOTAL GENERAL RECAUDADO ---");
        double total = 0;
        for (Vehiculo v : listaVehiculos) {
            total += v.calcularCosto();
        }
        System.out.printf("Total Recaudado: Q%.2f%n", total);
    }

    private static void mostrarTotalesPorTipo() {
        System.out.println("\n--- TOTAL RECAUDADO POR TIPO DE VEHÍCULO ---");

        HashMap<String, Double> totalesPorTipo = new HashMap<>();
        totalesPorTipo.put("Automóvil", 0.0);
        totalesPorTipo.put("Motocicleta", 0.0);

        for (Vehiculo v : listaVehiculos) {
            String tipo = v.getTipo();
            double acumuladoActual = totalesPorTipo.getOrDefault(tipo, 0.0);
            totalesPorTipo.put(tipo, acumuladoActual + v.calcularCosto());
        }

        for (String tipo : totalesPorTipo.keySet()) {
            System.out.printf("%-12s: Q%.2f%n", tipo, totalesPorTipo.get(tipo));
        }
    }
}