package practica6.ejercicio1.dto;

public final class ResultadoEntradaDTO {
    private final int idAsistente;
    private final EstadoEntrada estado;

    public ResultadoEntradaDTO(int idAsistente, EstadoEntrada estado) {
        this.idAsistente = idAsistente;
        this.estado = estado;
    }

    public int getIdAsistente() { return idAsistente; }
    public EstadoEntrada getEstado() { return estado; }
}
