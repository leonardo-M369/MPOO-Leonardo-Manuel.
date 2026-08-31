public class EspecificacionTecnica {
    private String categoria;
    private double pesoKg;
    private double dimensionAnchoCm;
    private double dimensionLargoCm;

    public EspecificacionTecnica(String categoria, double pesoKg, double dimensionAnchoCm, double dimensionLargoCm) {
        this.categoria = categoria;
        this.pesoKg = pesoKg;
        this.dimensionAnchoCm = dimensionAnchoCm;
        this.dimensionLargoCm = dimensionLargoCm;
    }

    public String getCategoria() { return categoria; }
    public double getPesoKg() { return pesoKg; }
    public double getDimensionAnchoCm() { return dimensionAnchoCm; }
    public double getDimensionLargoCm() { return dimensionLargoCm; }

    @Override
    public String toString() {
        return "Categoría: " + categoria + ", Peso: " + pesoKg + "kg, Dim: " + dimensionAnchoCm + "x" + dimensionLargoCm + "cm";
    }
}