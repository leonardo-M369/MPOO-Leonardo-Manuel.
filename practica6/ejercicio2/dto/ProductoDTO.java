package practica6.ejercicio2.dto;

public final class ProductoDTO {
    private final String id;
    private final String nombre;
    private final String precio;

    public ProductoDTO(String id, String nombre, String precio) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getPrecio() { return precio; }
}