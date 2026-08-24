public class Main {

    public static void main(String[] args) {

        // Construcción del arreglo polimórfico
        Mascota[] pacientes = {
                new Perro("P-001", "Luna", 14, 12.5, "Mestiza"),
                new Gato("G-001", "Milo", 24, 4.8, true),
                new Ave("A-001", "Piolín", 10, 0.4, "Canario")
        };

        // Recorrido con for-each
        for (Mascota paciente : pacientes) {
            // Mostrar información
            System.out.println(paciente.mostrarInformacion());

            // Emitir sonido
            paciente.emitirSonido();

            // Calcular costo
            System.out.println("Costo consulta: $" + paciente.calcularCostoConsulta());

            System.out.println("----------------------------------------");
        }
    }
}