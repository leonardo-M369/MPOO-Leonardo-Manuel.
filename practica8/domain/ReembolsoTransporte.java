package practica8.domain;
import java.time.LocalDate;

public class ReembolsoTransporte extends Reembolso {
    public ReembolsoTransporte(String folio, String empleado, double monto, LocalDate fechaGasto, boolean tieneComprobante, boolean autorizadoGerente, LocalDate fechaEvaluacion) {
        super(folio, empleado, monto, fechaGasto, tieneComprobante, autorizadoGerente, fechaEvaluacion);
    }
    @Override public double getLimitePermitido() { return 1500.0; }
}