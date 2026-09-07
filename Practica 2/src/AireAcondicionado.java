
public class AireAcondicionado {
    private boolean encendido;
    private int temperatura; // grados Celsius
    private int velocidadVentilador; // 1 a 3

    public AireAcondicionado(boolean encendido, int temperatura, int velocidadVentilador) {
        this.encendido = encendido;
        this.temperatura = temperatura;
        this.velocidadVentilador = velocidadVentilador;
    }

    public boolean isEncendido() { return encendido; }
    public int getTemperatura() { return temperatura; }
    public int getVelocidadVentilador() { return velocidadVentilador; }

    public void encender() { this.encendido = true; }
    public void apagar() { this.encendido = false; }

    public void ajustarTemperatura(int nuevaTemp) {
        if (nuevaTemp >= 16 && nuevaTemp <= 30) {
            this.temperatura = nuevaTemp;
        }
    }

    public void cambiarVelocidad(int nuevaVelocidad) {
        if (nuevaVelocidad >= 1 && nuevaVelocidad <= 3) {
            this.velocidadVentilador = nuevaVelocidad;
        }
    }

    public double calcularCostoPorHora(double precioKWh) {
        if (!encendido) return 0.0;
        double potenciaKW = 1.2 + (velocidadVentilador * 0.2);
        return potenciaKW * precioKWh;
    }

    public void verEstadoActual() {
        String estado = encendido ? "ENCENDIDO" : "APAGADO";
        System.out.println("Aire Acondicionado | Estado=[" + estado + "] | Temp=[" + temperatura + "°C] | Velocidad=[" + velocidadVentilador + "]");
    }
}