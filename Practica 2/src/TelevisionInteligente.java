public class TelevisionInteligente {
    private boolean encendida;
    private int canal;
    private int volumen;

    public TelevisionInteligente(boolean encendida, int canal, int volumen) {
        this.encendida = encendida;
        this.canal = canal;
        this.volumen = volumen;
    }

    public boolean isEncendida() { return encendida; }
    public int getCanal() { return canal; }
    public int getVolumen() { return volumen; }

    public void encender() { this.encendida = true; }
    public void apagar() { this.encendida = false; }

    public void cambiarCanal(int nuevoCanal) {
        if (nuevoCanal > 0) {
            this.canal = nuevoCanal;
        }
    }

    public void ajustarVolumen(int nuevoVolumen) {
        if (nuevoVolumen >= 0 && nuevoVolumen <= 100) {
            this.volumen = nuevoVolumen;
        }
    }

    public double calcularEnergiaConsumidaDiaria(double horasUso) {
        if (!encendida) return 0.0;
        return (150.0 * horasUso) / 1000.0; // kWh en un TV de 150W
    }

    public void verEstadoActual() {
        String estado = encendida ? "ENCENDIDA" : "APAGADA";
        System.out.println("Televisión Inteligente | Estado=[" + estado + "] | Canal=[" + canal + "] | Volumen=[" + volumen + "]");
    }
}