package mx.unam.fi.parkmanager;

public class ParkManagerException extends RuntimeException {
    private final String codigo;

    public ParkManagerException(String codigo, String mensaje) {
        super(mensaje);
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}