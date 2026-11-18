package practica8;

import practica8.domain.Reembolso;
import practica8.dto.*;
import practica8.factory.*;
import practica8.validator.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDate;

class Result {
    public static ReembolsoOutputDTO procesarReembolso(ReembolsoInputDTO input) {
        RegistroReembolso registro;
        switch (input.getTipo()) {
            case ALIMENTOS:
                registro = new RegistroAlimentos();
                break;
            case TRANSPORTE:
                registro = new RegistroTransporte();
                break;
            case HOSPEDAJE:
            default:
                registro = new RegistroHospedaje();
                break;
        }

        Reembolso reembolso = registro.crearReembolso(input);

        ValidadorReembolso v1 = new ValidadorComprobante();
        ValidadorReembolso v2 = new ValidadorPeriodo();
        ValidadorReembolso v3 = new ValidadorLimite();
        ValidadorReembolso v4 = new ValidadorGerencia();

        v1.setSiguiente(v2);
        v2.setSiguiente(v3);
        v3.setSiguiente(v4);

        ResultadoValidacion resultado = v1.validar(reembolso);

        return new ReembolsoOutputDTO(reembolso.getFolio(), input.getTipo(), resultado);
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String lineaFecha = br.readLine();
        LocalDate fechaEvaluacion = LocalDate.parse(lineaFecha.trim());

        String lineaSolicitud = br.readLine();
        String[] datos = lineaSolicitud.split("\\|");

        TipoReembolso tipo = TipoReembolso.valueOf(datos[0].trim());
        String folio = datos[1].trim();
        String empleado = datos[2].trim();
        double monto = Double.parseDouble(datos[3].trim());
        LocalDate fechaGasto = LocalDate.parse(datos[4].trim());
        boolean tieneComprobante = Boolean.parseBoolean(datos[5].trim());
        boolean autorizadoGerente = Boolean.parseBoolean(datos[6].trim());

        ReembolsoInputDTO input = new ReembolsoInputDTO(
                tipo, folio, empleado, monto, fechaGasto, tieneComprobante, autorizadoGerente, fechaEvaluacion
        );

        ReembolsoOutputDTO output = Result.procesarReembolso(input);

        System.out.println("REEMBOLSO " + output.getFolio());
        System.out.println("TIPO " + output.getTipo());
        System.out.println("RESULTADO " + output.getResultado());
    }
}