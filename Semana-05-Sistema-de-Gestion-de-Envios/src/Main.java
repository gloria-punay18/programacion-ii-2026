import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcion = 0;

        System.out.println("\n ---Nombre: Gloria Esperanza Punay Xocoxic---");
        System.out.println("\n ---Carne: 9941-25-22033---");


        while (opcion != 3) {
            System.out.println("\n ===SISTEMA DE ENVÍOS===");
            System.out.println("1. Registrar envío nacional");
            System.out.println("2. Registrar envío internacional");
            System.out.println("3. Salir");
            System.out.println("Seleccione una opción: ");

            opcion = leerEntero();
            if (opcion == 1) {
                registrarEnvioNacional();
            } else if (opcion == 2) {
                registrarEnvioInternacional();
            } else if (opcion == 3) {
                System.out.println("¡Gracias por utilizar el sistema de Envíos! ");
            } else {
                System.out.println("Opción inválida. Intente de nuevo. ");
            }

        }
    }

    private static void registrarEnvioNacional(){
        System.out.println("\n--- Registro de Envío Nacional ---");
        String codigo = leerTextoNoVacio("Ingrese el código del envío: ");
        String destinatario = leerTextoNoVacio("Ingrese el nombre del destinatario: ");
        double peso = leerDoublePositivo("Ingrese el peso en kg (mayor a 0): ");
        String departamento = leerTextoNoVacio ("Ingrese el departamento de destino: ");
        double distancia = leerDoublePositivo ("Ingrese la distancia en km (Mayor a 0 ): ");

        Envio miEnvio =new EnvioNacional(codigo, destinatario, peso, departamento, distancia);

        miEnvio.mostrarResumen(true);
    }


    private static void registrarEnvioInternacional() {
        System.out.println("\n--- Registro de Envío Internacional ---");
        String codigo = leerTextoNoVacio("Ingrese el código del envío: ");
        String destinatario = leerTextoNoVacio("Ingrese el nombre del destinatario: ");
        double peso = leerDoublePositivo("Ingrese el peso en kg (mayor a 0): ");
        String pais = leerTextoNoVacio("Ingrese el país de destino: ");

        Envio miEnvio = new EnvioInternacional(codigo, destinatario, peso, pais);

        miEnvio.mostrarResumen(true);
    }


    private static String leerTextoNoVacio(String mensaje){
        String texto = "";
        while (texto.trim().isEmpty()){
            System.out.println(mensaje);
            texto=scanner.nextLine();
            if (texto.trim().isEmpty()){
                System.out.println("Error: El campo no puede estar vacío.");
            }
        }
        return texto;
    }


    private static double leerDoublePositivo(String mensaje){
        double valor = -1;
        while (valor <= 0){
            System.out.println(mensaje);
            try {
                valor = Double.parseDouble(scanner.nextLine());
                if (valor<=0){
                    System.out.println("Error: El valor debe ser mayor a cero.");
                }
            } catch (NumberFormatException e){
                System.out.println("Error: Ingrese un número válido.");
            }
        }
        return valor;
    }


    private static int leerEntero(){
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}