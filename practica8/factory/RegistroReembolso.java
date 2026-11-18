package practica8.factory;

import practica8.domain.Reembolso;
import practica8.dto.ReembolsoInputDTO;

public abstract class RegistroReembolso {
    public abstract Reembolso crearReembolso(ReembolsoInputDTO input);
}