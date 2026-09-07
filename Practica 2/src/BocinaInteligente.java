public class BocinaInteligente {
    private boolean encendida;
    private int volumen; // 0 a 100
    private String cancionActual;

    public BocinaInteligente(boolean encendida, int volumen, String cancionActual) {
        this.encendida = encendida;
        this.volumen = volumen;
        this.cancionActual = cancionActual;
    }

    public boolean isEncendida() { return encendida; }
    public int getVolumen() { return volumen; }
    public String getCancionActual() { return cancionActual; }

    public void encender() { this.encendida = true; }
    public void apagar() { this.encendida = false; }

    public void cambiarVolumen(int nuevoVolumen) {
        if (nuevoVolumen >= 0 && nuevoVolumen <= 100) {
            this.volumen = nuevoVolumen;
        }
    }

    public void reproducirCancion(String cancion) {
        this.cancionActual = cancion;
        this.encendida = true;
    }

    public int estimarDecibeles() {
        if (!encendida) return 0;
        return 30 + (int)(volumen * 0.6); // estimación simple en dB
    }

    public void verEstadoActual() {
        String estado = encendida ? "ENCENDIDA" : "APAGADA";
        System.out.println("Bocina Inteligente | Estado=[" + estado + "] | Volumen=[" + volumen + "] | Canción=[" + cancionActual + "]");
    }
}
