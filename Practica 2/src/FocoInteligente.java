public class FocoInteligente {
    private boolean encendido;
    private int intensidad; // 0 a 100
    private double consumoWatts;

    public FocoInteligente(boolean encendido, int intensidad, double consumoWatts) {
        this.encendido = encendido;
        this.intensidad = intensidad;
        this.consumoWatts = consumoWatts;
    }

    public boolean isEncendido() { return encendido; }
    public int getIntensidad() { return intensidad; }
    public double getConsumoWatts() { return consumoWatts; }

    public void encender() {
        this.encendido = true;
        this.intensidad = 100;
    }

    public void apagar() {
        this.encendido = false;
        this.intensidad = 0;
    }

    public void cambiarIntensidad(int nuevaIntensidad) {
        if (nuevaIntensidad >= 0 && nuevaIntensidad <= 100) {
            this.intensidad = nuevaIntensidad;
            this.encendido = (nuevaIntensidad > 0);
        }
    }

    public double calcularConsumoEstimado(int horas) {
        if (!encendido) return 0.0;
        return (consumoWatts * (intensidad / 100.0) * horas) / 1000.0; // kWh
    }

    public void verEstadoActual() {
        String estado = encendido ? "ENCENDIDO" : "APAGADO";
        System.out.println("Foco Inteligente | Estado=[" + estado + "] | Intensidad=[" + intensidad + "%] | ConsumoBase=[" + consumoWatts + "W]");
    }
}