import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();

        // 1. Instanciación con los 3 propietarios requeridos
        Propietario p1 = new Propietario("Sylphiette", "SYLP072807", "UNAM FI");
        Propietario p2 = new Propietario("Roxy", "ROXY987654", "UNAM FI");
        Propietario p3 = new Propietario("Eris", "ERIS456789", "UNAM FI");

        Robot r1 = new Robot("Destructor", "Sumo", p1, new SistemaBateria("LiPo 3S", 2025));
        Robot r2 = new Robot("Chispita", "Sumo", p2, new SistemaBateria("Li-Ion", 2024));
        Robot r3 = new Robot("Megatron", "Sumo", p3, new SistemaBateria("LiPo 4S", 2026));

        // 2. Muestra contador compartido estático
        System.out.println("==================================================");
        System.out.println("TOTAL DE ROBOTS REGISTRADOS PARA LA BATALLA: " + Robot.getTotalRobots());
       

        // 3. Estado inicial
        System.out.println("--- ESTADO INICIAL DE LOS PARTICIPANTES ---");
        System.out.println(r1 + "\n");
        System.out.println(r2 + "\n");
        System.out.println(r3 + "\n");

        // 4. Simulación con Random
        System.out.println("= SIMULANDO COMBATE=");
        double aceleracionAleatoria1 = 2.0 + random.nextDouble() * 8.0;
        double danoAleatorio1 = 10.0 + random.nextDouble() * 40.0;

        double aceleracionAleatoria2 = 1.0 + random.nextDouble() * 5.0;
        double danoAleatorio2 = 50.0 + random.nextDouble() * 60.0;

        r1.acelerar(aceleracionAleatoria1);
        r1.recibirDano(danoAleatorio1);

        r2.acelerar(aceleracionAleatoria2);
        r2.recibirDano(danoAleatorio2);

        r3.acelerar(4.5);
        double potencia = r3.calcularPotenciaAtaque(12.5);
        System.out.println("Potencia de impacto calculada por Megatron: " + String.format("%.2f", potencia));

        // 5. Estado posterior a la batalla
        System.out.println("\n--- ESTADO POSTERIOR A LA BATALLA ---");
        System.out.println(r1 + "\n");
        System.out.println(r2 + "\n");
        System.out.println(r3 + "\n");

        // 6. Pruebas de Referencia e Igualdad
        System.out.println("================ EXPERIMENTO DE REFERENCIAS E IGUALDAD ================");
        
        Robot robotFavorito = r1; // Misma referencia en memoria
        System.out.println("Modificando 'r1' con 'r1.acelerar(3.0)'...");
        r1.acelerar(3.0);
        System.out.println("Estado consultado desde 'robotFavorito':\n" + robotFavorito);

        // Nuevo objeto con datos idénticos
        Robot r1Duplicado = new Robot("Destructor", "Sumo", p1, new SistemaBateria("LiPo 3S", 2025));

        System.out.println("\n--- RESULTADOS PRUEBA DE IGUALDAD ---");
        System.out.println("r1.equals(r1Duplicado)    : " + r1.equals(r1Duplicado) + "  (Nuevas instancias creadas con new)");
        System.out.println("r1.equals(robotFavorito) : " + r1.equals(robotFavorito) + "   (Misma dirección de memoria)");
    }
}