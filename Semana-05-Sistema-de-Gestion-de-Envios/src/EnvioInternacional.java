public class EnvioInternacional extends Envio {
    private String paisDestino;
    private static final double CARGO_FIJO = 75.00;

    public EnvioInternacional(String codigo, String destinatario, double pesoKg, String paisDestino) {
        super(codigo, destinatario, pesoKg);
        this.paisDestino = paisDestino;
    }

    public String getPaisDestino() { return paisDestino; }

    @Override
    public double calcularCostoFinal() {
        double base = calcularCostoBase();
        return base + CARGO_FIJO + (base * 0.12);
    }

    @Override
    public void mostrarResumen(boolean conDesglose) {
        if (!conDesglose) {
            super.mostrarResumen();
            return;
        }
        double base = calcularCostoBase();
        double recargo = base * 0.12;
        System.out.println("\n=== RESUMEN DETALLADO (ENVÍO INTERNACIONAL) ===");
        System.out.println("Código: " + getCodigo());
        System.out.println("Destinatario: " + getDestinatario());
        System.out.println("País: " + paisDestino);
        System.out.printf("Peso: %.2f kg%n", getPesoKg());
        System.out.printf("Costo base: Q%.2f%n", base);
        System.out.printf("Cargo fijo: Q%.2f%n", CARGO_FIJO);
        System.out.printf("Recargo (12%%): Q%.2f%n", recargo);
        System.out.printf("Costo final: Q%.2f%n", calcularCostoFinal());
    }
}