import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    enum TipoEstancia {
        NORMAL,
        NOCTURNA,
        FIN_SEMANA,
        MIXTA
    }

    public static String calcularEstancia(String tipoVehiculo, String fechaEntrada, String horaEntrada, String fechaSalida, String horaSalida) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            sdf.setLenient(false);

            Calendar calEntrada = Calendar.getInstance();
            calEntrada.setTime(sdf.parse(fechaEntrada + " " + horaEntrada));

            Calendar calSalida = Calendar.getInstance();
            calSalida.setTime(sdf.parse(fechaSalida + " " + horaSalida));

            if (!calSalida.after(calEntrada)) {
                return "INVALID";
            }

            double tarifaPorHora = 0.0;
            double maxBloque = 0.0;

            switch (tipoVehiculo.trim().toUpperCase()) {
                case "MOTOCICLETA":
                    tarifaPorHora = 15.0;
                    maxBloque = 100.0;
                    break;
                case "AUTOMOVIL":
                    tarifaPorHora = 25.0;
                    maxBloque = 180.0;
                    break;
                case "CAMIONETA":
                    tarifaPorHora = 35.0;
                    maxBloque = 250.0;
                    break;
                case "ELECTRICO":
                    tarifaPorHora = 20.0;
                    maxBloque = 150.0;
                    break;
                default:
                    return "INVALID";
            }

            long diferenciaMillis = calSalida.getTimeInMillis() - calEntrada.getTimeInMillis();
            double horasExactas = diferenciaMillis / (1000.0 * 60 * 60);
            long horasCobradas = (long) Math.ceil(horasExactas);

            double costoBase = 0.0;
            long horasRestantes = horasCobradas;

            while (horasRestantes > 0) {
                long horasEnBloque = Math.min(horasRestantes, 24);
                double costoSinCap = horasEnBloque * tarifaPorHora;
                double costoBloque = Math.min(costoSinCap, maxBloque);
                
                costoBase += costoBloque;
                horasRestantes -= horasEnBloque;
            }

            int diaSemanaEntrada = calEntrada.get(Calendar.DAY_OF_WEEK);
            int diaSemanaSalida = calSalida.get(Calendar.DAY_OF_WEEK);
            
            boolean esFinSemana = (diaSemanaEntrada == Calendar.SATURDAY || diaSemanaEntrada == Calendar.SUNDAY ||
                                   diaSemanaSalida == Calendar.SATURDAY || diaSemanaSalida == Calendar.SUNDAY);

            int horaEnt = calEntrada.get(Calendar.HOUR_OF_DAY);
            int horaSal = calSalida.get(Calendar.HOUR_OF_DAY);
            
            boolean mismaFecha = (calEntrada.get(Calendar.YEAR) == calSalida.get(Calendar.YEAR) &&
                                  calEntrada.get(Calendar.DAY_OF_YEAR) == calSalida.get(Calendar.DAY_OF_YEAR));

            boolean esNocturna = (horaEnt >= 20 || horaSal < 6 || !mismaFecha);

            double costoFinal = costoBase;

            if (esFinSemana) {
                costoFinal *= 1.20;
            }
            if (esNocturna) {
                costoFinal *= 1.15;
            }
            if (tipoVehiculo.trim().equalsIgnoreCase("ELECTRICO")) {
                costoFinal *= 0.90;
            }

            TipoEstancia tipoEstancia;
            if (esFinSemana && esNocturna) {
                tipoEstancia = TipoEstancia.MIXTA;
            } else if (esFinSemana) {
                tipoEstancia = TipoEstancia.FIN_SEMANA;
            } else if (esNocturna) {
                tipoEstancia = TipoEstancia.NOCTURNA;
            } else {
                tipoEstancia = TipoEstancia.NORMAL;
            }

            return String.format(Locale.US, "%d %.2f %s", horasCobradas, costoFinal, tipoEstancia);

        } catch (Exception e) {
            return "INVALID";
        }
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String tipoVehiculo = bufferedReader.readLine();

        String fechaEntrada = bufferedReader.readLine();

        String horaEntrada = bufferedReader.readLine();

        String fechaSalida = bufferedReader.readLine();

        String horaSalida = bufferedReader.readLine();

        String result = Result.calcularEstancia(tipoVehiculo, fechaEntrada, horaEntrada, fechaSalida, horaSalida);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
