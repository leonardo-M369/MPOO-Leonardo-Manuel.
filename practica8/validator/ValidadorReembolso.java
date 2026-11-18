package practica8.validator;

import practica8.domain.Reembolso;
import practica8.dto.ResultadoValidacion;

public abstract class ValidadorReembolso {
    protected ValidadorReembolso siguiente;

    public void setSiguiente(ValidadorReembolso siguiente) {
        this.siguiente = siguiente;
    }

    public abstract ResultadoValidacion validar(Reembolso reembolso);
}