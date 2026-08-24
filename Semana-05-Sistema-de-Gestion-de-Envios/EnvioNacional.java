public class EnvioNacional extends Envio {
    private String departamento;
    private double distanciaKm;

    public EnvioNacional(String codigo, String destinatario, double pesoKg, String departamento, double distanciaKm) {
        super(codigo, destinatario, pesoKg);
        this.departamento = departamento;
        this.distanciaKm = distanciaKm;
    }

    public String getDepartamento() { return departamento; }
    public double getDistanciaKm() { return distanciaKm; }

    @Override
    public double calcularCostoFinal() {
        return calcularCostoBase() + (distanciaKm * 0.50);
    }

    @Override
    public void mostrarResumen(boolean conDesglose) {
        if (!conDesglose) {
            super.mostrarResumen();
            return;
        }
        double cargoDistancia = distanciaKm * 0.50;
        System.out.println("\n=== RESUMEN DETALLADO (ENVÍO NACIONAL) ===");
        System.out.println("Código: " + getCodigo());
        System.out.println("Destinatario: " + getDestinatario());
        System.out.println("Departamento: " + departamento);
        System.out.printf("Peso: %.2f kg%n", getPesoKg());
        System.out.printf("Distancia: %.2f km%n", distanciaKm);
        System.out.printf("Costo base: Q%.2f%n", calcularCostoBase());
        System.out.printf("Cargo distancia: Q%.2f%n", cargoDistancia);
        System.out.printf("Costo final: Q%.2f%n", calcularCostoFinal());
    }
}