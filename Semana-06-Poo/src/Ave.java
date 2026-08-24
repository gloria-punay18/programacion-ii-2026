public class Ave extends Mascota {

    private String tipo;

    public Ave(
            String codigoPaciente,
            String nombre,
            int edadMeses,
            double pesoKg,
            String tipo) {

        super(codigoPaciente, nombre, edadMeses, pesoKg);
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    @Override
    public void emitirSonido() {
        System.out.println(getNombre() + ": ¡Pío, pío!");
    }

    @Override
    public double calcularCostoConsulta() {
        return 10000;
    }
}