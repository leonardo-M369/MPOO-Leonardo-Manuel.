package practica8.dto;

public final class ReembolsoOutputDTO {
    private final String folio;
    private final TipoReembolso tipo;
    private final ResultadoValidacion resultado;

    public ReembolsoOutputDTO(
            String folio,
            TipoReembolso tipo,
            ResultadoValidacion resultado) {
        this.folio = folio;
        this.tipo = tipo;
        this.resultado = resultado;
    }

    public String getFolio() { return folio; }
    public TipoReembolso getTipo() { return tipo; }
    public ResultadoValidacion getResultado() { return resultado; }
}