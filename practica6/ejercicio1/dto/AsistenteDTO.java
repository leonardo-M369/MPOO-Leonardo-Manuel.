package practica6.ejercicio1.dto;

public final class AsistenteDTO {
    private final String id;
    private final String nombre;
    private final String tipo;

    public AsistenteDTO(String id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getTipo() { return tipo; }
}