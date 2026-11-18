package practica6.ejercicio2.domain;

public class ElementoPedido {
    private final Producto producto;
    private int cantidad;

    public ElementoPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

    public void agregarCantidad(int adicional) {
        this.cantidad += adicional;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
