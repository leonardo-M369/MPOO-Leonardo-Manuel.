package practica6.ejercicio1.service;

import practica6.ejercicio1.dto.*;
import java.util.List;

public interface ControlAccesoService {
    int AFORO_MAXIMO = 10;

    boolean registrarAsistente(AsistenteDTO asistente);
    EstadoEntrada registrarEntrada(int idAsistente);
    EstadoSalida registrarSalida(int idAsistente);
    List<ResultadoEntradaDTO> registrarEntradasMasivas(List<Integer> identificadores);
    ReporteDTO generarReporte();
}
