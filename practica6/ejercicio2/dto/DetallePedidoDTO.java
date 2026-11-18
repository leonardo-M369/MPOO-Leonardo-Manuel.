package practica6.ejercicio2.dto;

public final class DetallePedidoDTO {
    private final int idProducto;
    private final String nombre;
    private final int cantidad;

    public DetallePedidoDTO(int idProducto, String nombre, int cantidad) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
    }

    public int getIdProducto() { return idProducto; }
    public String getNombre() { return nombre; }
    public int getCantidad() { return cantidad; }
}