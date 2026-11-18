package practica8.validator;

import practica8.domain.Reembolso;
import practica8.dto.ResultadoValidacion;
import java.time.temporal.ChronoUnit;

public class ValidadorPeriodo extends ValidadorReembolso {
    @Override
    public ResultadoValidacion validar(Reembolso reembolso) {
        long dias = ChronoUnit.DAYS.between(reembolso.getFechaGasto(), reembolso.getFechaEvaluacion());
        if (dias > 30) {
            return ResultadoValidacion.FUERA_DE_PERIODO;
        }
        if (siguiente != null) {
            return siguiente.validar(reembolso);
        }
        return ResultadoValidacion.APROBADO;
    }
}