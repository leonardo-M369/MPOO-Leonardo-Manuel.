package practica8.validator;

import practica8.domain.Reembolso;
import practica8.dto.ResultadoValidacion;

public class ValidadorGerencia extends ValidadorReembolso {
    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        if (reembolso.getMonto() > 2000.0 && !reembolso.estaAutorizadoGerente()) {
            return ResultadoValidacion.REQUIERE_AUTORIZACION;
        }
        if (siguiente != null) {
            return siguiente.validar(reembolso);
        }
        return ResultadoValidacion.APROBADO;
    }
}