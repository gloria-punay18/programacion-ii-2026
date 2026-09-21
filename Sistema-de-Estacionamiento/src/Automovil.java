public class Automovil extends Vehiculo {


    public Automovil(String placa, String propietario, String horaIngreso, double horasUtilizadas) {
        super(placa, propietario, horaIngreso, horasUtilizadas);
    }


    @Override
    public double calcularCosto() {
        double subtotal = getHorasUtilizadas() * 10.00;


        if (getHorasUtilizadas() > 5) {
            subtotal = subtotal - (subtotal * 0.10);
        }

        return subtotal;
    }

    @Override
    public String getTipo() {
        return "Automóvil";
    }
}