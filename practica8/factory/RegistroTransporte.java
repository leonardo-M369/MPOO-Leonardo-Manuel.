package practica8.factory;

import practica8.domain.Reembolso;
import practica8.domain.ReembolsoTransporte;
import practica8.dto.ReembolsoInputDTO;

public class RegistroTransporte extends RegistroReembolso {
    @Override
    public Reembolso crearReembolso(ReembolsoInputDTO input) {
        return new ReembolsoTransporte(
                input.getFolio(), input.getEmpleado(), input.getMonto(),
                input.getFechaGasto(), input.tieneComprobante(),
                input.estaAutorizadoGerente(), input.getFechaEvaluacion()
        );
    }
}