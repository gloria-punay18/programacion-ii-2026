public abstract class Vehiculo {

    private String placa;
    private String propietario;
    private String horaIngreso;
    private double horasUtilizadas;


    public Vehiculo(String placa, String propietario, String horaIngreso, double horasUtilizadas) {
        this.placa = placa;
        this.propietario = propietario;
        this.horaIngreso = horaIngreso;
        this.horasUtilizadas = horasUtilizadas;
    }


    public abstract double calcularCosto();


    public abstract String getTipo();


    public String getPlaca() {
        return placa;
    }

    public String getPropietario() {
        return propietario;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public double getHorasUtilizadas() {
        return horasUtilizadas;
    }

    // 5. Método para mostrar la información general
    public void mostrarInformacion() {
        System.out.println("----------------------------------------");
        System.out.println("Tipo: " + getTipo());
        System.out.println("Placa: " + placa);
        System.out.println("Propietario: " + propietario);
        System.out.println("Hora de ingreso: " + horaIngreso);
        System.out.println("Horas utilizadas: " + horasUtilizadas);
        System.out.println("Total a pagar: Q" + calcularCosto());
    }
}