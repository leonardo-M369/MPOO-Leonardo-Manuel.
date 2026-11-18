package practica6.ejercicio2.domain;

import practica6.ejercicio2.strategy.*;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Discountable {
    private final List<ElementoPedido> elementos;
    private DescuentoStrategy descuentoStrategy;

    private Pedido() {
        this.elementos = new ArrayList<>();
        this.descuentoStrategy = new DescuentoRegular();
    }

    public static Pedido empty() {
        return new Pedido();
    }

    @Override
    public void setDiscount(DescuentoStrategy descuentoStrategy) {
        if (descuentoStrategy != null) {
            this.descuentoStrategy = descuentoStrategy;
        }
    }

    public void agregarOActualizarProducto(Producto producto, int cantidad) {
        for (ElementoPedido elem : elementos) {
            if (elem.getProducto().getId() == producto.getId()) {
                elem.agregarCantidad(cantidad);
                return;
            }
        }
        elementos.add(new ElementoPedido(producto, cantidad));
    }

    public List<ElementoPedido> getElementos() { return elementos; }

    public double calcularSubtotal() {
        double subtotal = 0.0;
        for (ElementoPedido elem : elementos) {
            subtotal += elem.getSubtotal();
        }
        return subtotal;
    }

    public double calcularDescuento() {
        double subtotal = calcularSubtotal();
        if (descuentoStrategy == null) {
            return 0.0;
        }
        return descuentoStrategy.calcularDescuento(subtotal);
    }

    public double calcularTotal() {
        return calcularSubtotal() - calcularDescuento();
    }
}