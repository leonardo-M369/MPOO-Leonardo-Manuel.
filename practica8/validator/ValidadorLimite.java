package practica8.validator;

import practica8.domain.Reembolso;
import practica8.dto.ResultadoValidacion;

public class ValidadorLimite extends ValidadorReembolso {
    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        if (reembolso.getMonto() > reembolso.getLimitePermitido()) {
            return ResultadoValidacion.EXCEDE_LIMITE;
        }
        if (siguiente != null) {
            return siguiente.validar(reembolso);
        }
        return ResultadoValidacion.APROBADO;
    }
}