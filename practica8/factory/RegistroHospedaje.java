package practica8.factory;

import practica8.domain.Reembolso;
import practica8.domain.ReembolsoHospedaje;
import practica8.dto.ReembolsoInputDTO;

public class RegistroHospedaje extends RegistroReembolso {
    @Override
    public Reembolso crearReembolso(ReembolsoInputDTO input) {
        return new ReembolsoHospedaje(
                input.getFolio(), input.getEmpleado(), input.getMonto(),
                input.getFechaGasto(), input.tieneComprobante(),
                input.estaAutorizadoGerente(), input.getFechaEvaluacion()
        );
    }
}