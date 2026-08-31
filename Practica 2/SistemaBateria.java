public class SistemaBateria {
    private String tipoBateria;
    private double porcentajeEnergia;
    private int anioFabricacion;

    public SistemaBateria(String tipoBateria, int anioFabricacion) {
        this.tipoBateria = tipoBateria;
        this.anioFabricacion = anioFabricacion;
        this.porcentajeEnergia = 100.0;
    }

    public double getPorcentajeEnergia() {
        return porcentajeEnergia;
    }

    public void consumirEnergia(double cantidad) {
        this.porcentajeEnergia = Math.max(0.0, this.porcentajeEnergia - cantidad);
    }

    public void recargarEnergia(double cantidad) {
        this.porcentajeEnergia = Math.min(100.0, this.porcentajeEnergia + cantidad);
    }

    @Override
    public String toString() {
        return tipoBateria + " [" + String.format("%.1f", porcentajeEnergia) + "%] (Año: " + anioFabricacion + ")";
    }
}