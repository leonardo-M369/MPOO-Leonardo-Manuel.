package practica6.ejercicio2.dto;

import java.util.List;

public final class ResumenPedidoDTO {
    private final List<DetallePedidoDTO> detalles;
    private final double subtotal;
    private final double descuento;
    private final double total;

    public ResumenPedidoDTO(List<DetallePedidoDTO> detalles, double subtotal, double descuento, double total) {
        this.detalles = detalles;
        this.subtotal = subtotal;
        this.descuento = descuento;
        this.total = total;
    }

    public List<DetallePedidoDTO> getDetalles() { return detalles; }
    public double getSubtotal() { return subtotal; }
    public double getDescuento() { return descuento; }
    public double getTotal() { return total; }
}