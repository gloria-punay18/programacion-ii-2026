public class Envio {
    private String codigo;
    private String destinatario;
    private double pesoKg;

    // Constructor
    public Envio(String codigo, String destinatario, double pesoKg) {
        this.codigo = codigo;
        this.destinatario = destinatario;
        this.pesoKg = pesoKg;
    }

    // Getters (Encapsulamiento)
    public String getCodigo() {
        return codigo;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    // Método para calcular el costo base (Q10.00 por Kg)
    public double calcularCostoBase() {
        return pesoKg * 10.0;
    }

    // Método base para el costo final
    public double calcularCostoFinal() {
        return calcularCostoBase();
    }

    // Sobrecarga Versión 1: Sin parámetros (solo info principal)
    public void mostrarResumen() {
        System.out.println("\n=== RESUMEN DE ENVÍO ===");
        System.out.println("Código: " + codigo);
        System.out.println("Destinatario: " + destinatario);
        System.out.printf("Costo Final: Q%.2f%n", calcularCostoFinal());
    }

    // Sobrecarga Versión 2: Con parámetro booleano para desglose completo
    public void mostrarResumen(boolean conDesglose) {
        if (!conDesglose) {
            mostrarResumen();
            return;
        }
        System.out.println("\n=== RESUMEN DETALLADO DE ENVÍO ===");
        System.out.println("Código: " + codigo);
        System.out.println("Destinatario: " + destinatario);
        System.out.printf("Peso del paquete: %.2f kg%n", pesoKg);
        System.out.printf("Costo base: Q%.2f%n", calcularCostoBase());
        System.out.printf("Costo final: Q%.2f%n", calcularCostoFinal());
    }
}