package practica6.ejercicio2.strategy;

public class DescuentoMayoreo implements DescuentoStrategy {
    @Override
    public double calcularDescuento(double subtotal) {
        if (subtotal >= 5000.00) {
            return subtotal * 0.15;
        }
        return 0.0;
    }
}