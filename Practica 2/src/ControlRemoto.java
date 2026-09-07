public class ControlRemoto {
    private FocoInteligente foco;
    private AireAcondicionado aire;
    private BocinaInteligente bocina;
    private CerraduraInteligente cerradura;
    private TelevisionInteligente television;

    public void setFoco(FocoInteligente foco) { this.foco = foco; }
    public void setAire(AireAcondicionado aire) { this.aire = aire; }
    public void setBocina(BocinaInteligente bocina) { this.bocina = bocina; }
    public void setCerradura(CerraduraInteligente cerradura) { this.cerradura = cerradura; }
    public void setTelevision(TelevisionInteligente television) { this.television = television; }

    public void modoNoche() {
        System.out.println("\n=== ACTIVANDO MODO NOCHE ===");
        if (foco != null) foco.cambiarIntensidad(10);
        if (aire != null) {
            aire.encender();
            aire.ajustarTemperatura(24);
            aire.cambiarVelocidad(1);
        }
        if (bocina != null) bocina.apagar();
        if (cerradura != null) cerradura.bloquear();
        if (television != null) television.apagar();
    }

    public void modoBienvenida() {
        System.out.println("\n=== ACTIVANDO MODO BIENVENIDA ===");
        if (foco != null) foco.encender();
        if (aire != null) {
            aire.encender();
            aire.ajustarTemperatura(21);
            aire.cambiarVelocidad(2);
        }
        if (bocina != null) bocina.reproducirCancion("Spiral - LONGMAN");
        if (cerradura != null) cerradura.desbloquear();
        if (television != null) television.encender();
    }

    public void modoAhorroEnergia() {
        System.out.println("\n=== ACTIVANDO MODO AHORRO DE ENERGÍA ===");
        if (foco != null) foco.apagar();
        if (aire != null) aire.apagar();
        if (bocina != null) bocina.apagar();
        if (cerradura != null) cerradura.bloquear();
        if (television != null) television.apagar();
    }

    public void verEstadoActual() {
        System.out.println("\n------------------------------------------------");
        if (foco != null) foco.verEstadoActual();
        if (aire != null) aire.verEstadoActual();
        if (bocina != null) bocina.verEstadoActual();
        if (cerradura != null) cerradura.verEstadoActual();
        if (television != null) television.verEstadoActual();
        System.out.println("------------------------------------------------");
    }
}
