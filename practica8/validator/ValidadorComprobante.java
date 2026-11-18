package practica8.validator;

import practica8.domain.Reembolso;
import practica8.dto.ResultadoValidacion;

public class ValidadorComprobante extends ValidadorReembolso {
    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        if (!reembolso.tieneComprobante()) {
            return ResultadoValidacion.SIN_COMPROBANTE;
        }
        if (siguiente != null) {
            return siguiente.validar(reembolso);
        }
        return ResultadoValidacion.APROBADO;
    }
}