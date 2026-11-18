package practica8.domain;
import java.time.LocalDate;

public abstract class Reembolso {
    protected final String folio;
    protected final String empleado;
    protected final double monto;
    protected final LocalDate fechaGasto;
    protected final boolean tieneComprobante;
    protected final boolean autorizadoGerente;
    protected final LocalDate fechaEvaluacion;

    public Reembolso(String folio, double monto) {
        this(folio, "Desconocido", monto, LocalDate.now(), false, false, LocalDate.now());
    }

    public Reembolso(String folio, String empleado, double monto, LocalDate fechaGasto, 
                     boolean tieneComprobante, boolean autorizadoGerente, LocalDate fechaEvaluacion) {
        this.folio = folio;
        this.empleado = empleado;
        this.monto = monto;
        this.fechaGasto = fechaGasto;
        this.tieneComprobante = tieneComprobante;
        this.autorizadoGerente = autorizadoGerente;
        this.fechaEvaluacion = fechaEvaluacion;
    }

    public abstract double getLimitePermitido();

    public String getFolio() { return folio; }
    public String getEmpleado() { return empleado; }
    public double getMonto() { return monto; }
    public LocalDate getFechaGasto() { return fechaGasto; }
    public boolean tieneComprobante() { return tieneComprobante; }
    public boolean estaAutorizadoGerente() { return autorizadoGerente; }
    public LocalDate getFechaEvaluacion() { return fechaEvaluacion; }
}