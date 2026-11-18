package practica6.ejercicio1;

import practica6.ejercicio1.dto.*;
import practica6.ejercicio1.service.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String asistentes = br.readLine();
        String operaciones = br.readLine();

        ControlAccesoService servicio = new ControlAccesoServiceImpl();

        if (asistentes != null && !asistentes.isBlank() && !asistentes.equals("-")) {
            String[] registros = asistentes.split(";");
            for (String registro : registros) {
                String[] datos = registro.split("\\|", 3);
                AsistenteDTO dto = new AsistenteDTO(datos[0].trim(), datos[1].trim(), datos[2].trim());
                servicio.registrarAsistente(dto);
            }
        }

        StringBuilder salida = new StringBuilder();

        if (operaciones != null && !operaciones.isBlank() && !operaciones.equals("-")) {
            String[] lista = operaciones.split(";");
            for (String operacion : lista) {
                String[] datos = operacion.split("\\|", 2);
                String accion = datos[0].trim();

                if (accion.equals("ENTRADA")) {
                    int id = Integer.parseInt(datos[1].trim());
                    EstadoEntrada estado = servicio.registrarEntrada(id);
                    salida.append("ENTRADA ").append(id).append(" ").append(estado).append("\n");
                } else if (accion.equals("SALIDA")) {
                    int id = Integer.parseInt(datos[1].trim());
                    EstadoSalida estado = servicio.registrarSalida(id);
                    salida.append("SALIDA ").append(id).append(" ").append(estado).append("\n");
                } else if (accion.equals("MASIVA")) {
                    String[] ids = datos[1].split(",");
                    List<Integer> identificadores = new ArrayList<>();
                    for (String id : ids) {
                        identificadores.add(Integer.parseInt(id.trim()));
                    }
                    List<ResultadoEntradaDTO> resultados = servicio.registrarEntradasMasivas(identificadores);
                    for (ResultadoEntradaDTO res : resultados) {
                        salida.append("ENTRADA ").append(res.getIdAsistente()).append(" ").append(res.getEstado()).append("\n");
                    }
                }
            }
        }

        ReporteDTO reporte = servicio.generarReporte();
        salida.append(String.format(
                "REPORTE %d %d %d %d %d %d %.2f",
                reporte.getRegistrados(),
                reporte.getDisponibles(),
                reporte.getAforo(),
                reporte.getAlumnos(),
                reporte.getProfesores(),
                reporte.getInvitados(),
                reporte.getOcupacion()
        ));

        System.out.print(salida.toString());
    }
}