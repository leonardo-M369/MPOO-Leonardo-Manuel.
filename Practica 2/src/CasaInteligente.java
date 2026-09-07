public class CasaInteligente {
    private FocoInteligente foco;
    private AireAcondicionado aire;
    private BocinaInteligente bocina;
    private CerraduraInteligente cerradura;
    private TelevisionInteligente television;
    private ControlRemoto control;

    public CasaInteligente(FocoInteligente foco, AireAcondicionado aire, BocinaInteligente bocina, CerraduraInteligente cerradura, TelevisionInteligente television, ControlRemoto control) {
        this.foco = foco;
        this.aire = aire;
        this.bocina = bocina;
        this.cerradura = cerradura;
        this.television = television;
        this.control = control;
    }

    public static void main(String[] args) {
        // 1. Instanciar los 5 dispositivos con estado inicial
        FocoInteligente foco = new FocoInteligente(false, 0, 12.5);
        AireAcondicionado aire = new AireAcondicionado(false, 25, 1);
        BocinaInteligente bocina = new BocinaInteligente(false, 20, "Sin reproducción");
        CerraduraInteligente cerradura = new CerraduraInteligente(true);
        TelevisionInteligente tv = new TelevisionInteligente(false, 5, 15);

        // 2. Instanciar el control remoto y registrar las mismas referencias
        ControlRemoto control = new ControlRemoto();
        control.setFoco(foco);
        control.setAire(aire);
        control.setBocina(bocina);
        control.setCerradura(cerradura);
        control.setTelevision(tv);

        // 3. Crear la CasaInteligente (Composición)
        CasaInteligente casa = new CasaInteligente(foco, aire, bocina, cerradura, tv, control);

        System.out.println("=== ESTADO INICIAL DE LA CASA ===");
        control.verEstadoActual();

        // 4. Manipulación individual directa desde main
        System.out.println("\n>>> Manipulación individual desde main (Encendiendo televisión y cambiando canal)...");
        tv.encender();
        tv.cambiarCanal(7);
        control.verEstadoActual();

        // 5. Demostración de modos del Control Remoto
        control.modoBienvenida();
        control.verEstadoActual();

        control.modoNoche();
        control.verEstadoActual();

        control.modoAhorroEnergia();
        control.verEstadoActual();
    }
}