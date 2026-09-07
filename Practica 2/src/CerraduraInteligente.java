public class CerraduraInteligente {
    private boolean bloqueada;
    private int intentosFallidos;
    private boolean alarmaActivada;

    public CerraduraInteligente(boolean bloqueada) {
        this.bloqueada = bloqueada;
        this.intentosFallidos = 0;
        this.alarmaActivada = false;
    }

    public boolean isBloqueada() { return bloqueada; }
    public int getIntentosFallidos() { return intentosFallidos; }
    public boolean isAlarmaActivada() { return alarmaActivada; }

    public void bloquear() {
        this.bloqueada = true;
    }

    public void desbloquear() {
        if (!alarmaActivada) {
            this.bloqueada = false;
            this.intentosFallidos = 0;
        }
    }

    public void registrarIntentoFallido() {
        this.intentosFallidos++;
        if (this.intentosFallidos >= 3) {
            this.alarmaActivada = true;
            this.bloqueada = true;
        }
    }

    public void reiniciarAlarma() {
        this.alarmaActivada = false;
        this.intentosFallidos = 0;
    }

    public void verEstadoActual() {
        String estadoSeguro = bloqueada ? "BLOQUEADA" : "DESBLOQUEADA";
        String estadoAlarma = alarmaActivada ? "ACTIVADA" : "NORMAL";
        System.out.println("Cerradura Inteligente | Cerrojo=[" + estadoSeguro + "] | IntentosFallidos=[" + intentosFallidos + "] | Alarma=[" + estadoAlarma + "]");
    }
}
