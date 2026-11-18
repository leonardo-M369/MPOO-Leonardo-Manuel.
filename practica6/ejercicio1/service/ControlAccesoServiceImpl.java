package practica6.ejercicio1.service;

import practica6.ejercicio1.domain.Asistente;
import practica6.ejercicio1.domain.TipoAsistente;
import practica6.ejercicio1.dto.*;

import java.util.*;

public class ControlAccesoServiceImpl implements ControlAccesoService {

    private static final Map<Integer, Asistente> registrados = new LinkedHashMap<>();
    private static final Set<Integer> personasDentro = new HashSet<>();

    @Override
    public boolean registrarAsistente(AsistenteDTO dto) {
        if (dto == null || dto.getId() == null) {
            return false;
        }

        try {
            int id = Integer.parseInt(dto.getId().trim());
            
            if (registrados.containsKey(id)) {
                return false;
            }

            TipoAsistente tipo = TipoAsistente.valueOf(dto.getTipo().trim().toUpperCase());
            Asistente asistente = new Asistente(id, dto.getNombre().trim(), tipo);
            registrados.put(id, asistente);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public EstadoEntrada registrarEntrada(int idAsistente) {
        if (!registrados.containsKey(idAsistente)) {
            return EstadoEntrada.NO_REGISTRADO;
        }

        if (personasDentro.contains(idAsistente)) {
            return EstadoEntrada.YA_DENTRO;
        }

        if (personasDentro.size() >= AFORO_MAXIMO) {
            return EstadoEntrada.AFORO_COMPLETO;
        }

        personasDentro.add(idAsistente);
        return EstadoEntrada.AUTORIZADO;
    }

    @Override
    public EstadoSalida registrarSalida(int idAsistente) {
        if (!registrados.containsKey(idAsistente)) {
            return EstadoSalida.NO_REGISTRADO;
        }

        if (!personasDentro.contains(idAsistente)) {
            return EstadoSalida.NO_ESTA_DENTRO;
        }

        personasDentro.remove(idAsistente);
        return EstadoSalida.AUTORIZADA;
    }

    @Override
    public List<ResultadoEntradaDTO> registrarEntradasMasivas(List<Integer> identificadores) {
        List<ResultadoEntradaDTO> resultados = new ArrayList<>();

        if (identificadores == null) {
            return resultados;
        }

        for (Integer id : identificadores) {
            if (personasDentro.size() >= AFORO_MAXIMO) {
                break;
            }

            EstadoEntrada estado = registrarEntrada(id);
            resultados.add(new ResultadoEntradaDTO(id, estado));
        }

        return resultados;
    }

    @Override
    public ReporteDTO generarReporte() {
        int totalRegistrados = registrados.size();
        int aforoActual = personasDentro.size();
        int disponibles = AFORO_MAXIMO - aforoActual;

        int alumnos = 0;
        int profesores = 0;
        int invitados = 0;

        for (Integer id : personasDentro) {
            Asistente a = registrados.get(id);
            if (a != null) {
                switch (a.getTipo()) {
                    case ALUMNO -> alumnos++;
                    case PROFESOR -> profesores++;
                    case INVITADO -> invitados++;
                }
            }
        }

        double ocupacion = ((double) aforoActual / AFORO_MAXIMO) * 100.0;

        return new ReporteDTO(
                totalRegistrados,
                disponibles,
                aforoActual,
                alumnos,
                profesores,
                invitados,
                ocupacion
        );
    }
}
