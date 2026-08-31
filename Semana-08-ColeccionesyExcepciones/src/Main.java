import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Mascota> pacientes = new ArrayList<>();

        System.out.println("=== SISTEMA VETERINARIO VETCARE ===");
        System.out.println("--- CASO A: REGISTRO CON ARRAYLIST ---");


        System.out.print("Ingrese un número para iniciar el proceso (ej. 1): ");
        String entrada = scanner.nextLine();

        try {
            int opcion = Integer.parseInt(entrada);
            System.out.println("Opción válida (" + opcion + "). Iniciando registro...\n");
        } catch (NumberFormatException e) {
            System.out.println("Debe ingresar una opción numérica. El programa continúa.\n");
        }


        pacientes.add(new Perro("VET-001", "Luna", 14, 24.5, "Mestiza"));
        pacientes.add(new Gato("VET-002", "Milo", 24, 4.8, true));
        pacientes.add(new Ave("VET-003", "Piolín", 10, 0.4, "Exótica"));
        System.out.println("-> Se registraron 3 mascotas exitosamente.");


        System.out.println("Cantidad actual de pacientes: " + pacientes.size());


        System.out.println("\n--- LISTA DE PACIENTES REGISTRADOS ---");
        for (Mascota paciente : pacientes) {
            System.out.println(paciente.mostrarInformacion());
            paciente.emitirSonido();
            System.out.println("Costo consulta: Q" + paciente.calcularCostoConsulta());
            System.out.println("-----------------------------");
        }


        Mascota eliminada = pacientes.remove(0);
        System.out.println("\n-> Se ha eliminado de la lista a: " + eliminada.getNombre());


        System.out.println("\n--- LISTA ACTUALIZADA (" + pacientes.size() + " PACIENTES) ---");
        for (Mascota paciente : pacientes) {
            System.out.println(paciente.mostrarInformacion());
        }

        scanner.close();
    }
}