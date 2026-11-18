package practica8.factory;

import practica8.domain.Reembolso;
import practica8.domain.ReembolsoAlimentos;
import practica8.dto.ReembolsoInputDTO;

public class RegistroAlimentos extends RegistroReembolso {
    @Override
    public Reembolso crearReembolso(ReembolsoInputDTO input) {
        return new ReembolsoAlimentos(
                input.getFolio(), input.getEmpleado(), input.getMonto(),
                input.getFechaGasto(), input.tieneComprobante(),
                input.estaAutorizadoGerente(), input.getFechaEvaluacion()
        );
    }
}