package mx.unam.fi.parkmanager;

public abstract class Boleto {
    protected double precioBase;
    protected double descuentoPorcentaje;

    public Boleto(double precioBase) {
        this.precioBase = precioBase;
        this.descuentoPorcentaje = 0.0;
    }

    public abstract String getTipo();

    public double getPrecioBase() {
        return precioBase;
    }

    public double getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(double descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public void removerDescuento() {
        this.descuentoPorcentaje = 0.0;
    }

    public double calcularPrecioUnitarioFinal() {
        return precioBase * (1.0 - (descuentoPorcentaje / 100.0));
    }
}
