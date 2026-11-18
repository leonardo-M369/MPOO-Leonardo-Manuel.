package practica6.ejercicio2.strategy;

public class DescuentoFrecuente implements DescuentoStrategy {
    @Override
    public double calcularDescuento(double subtotal) {
        return subtotal * 0.10;
    }
}