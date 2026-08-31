import java.util.Objects;
import java.util.UUID;

public class Robot {
    // Atributo de clase (static) para el contador de robots
    private static int totalRobots = 0;

    // Atributos de instancia
    private final UUID id;
    private String nombre;
    private String categoria;
    private double velocidadActual;
    private boolean estaActivo;

    // Atributos por composición
    private Propietario propietario;
    private SistemaBateria bateria;

    public Robot(String nombre, String categoria, Propietario propietario, SistemaBateria bateria) {
        this.id = UUID.randomUUID(); // Identidad única inmutable
        this.nombre = nombre;
        this.categoria = categoria;
        this.propietario = propietario;
        this.bateria = bateria;
        this.velocidadActual = 0.0;
        this.estaActivo = true;

        totalRobots++; // Incrementa el contador compartido
    }

    public static int getTotalRobots() {
        return totalRobots;
    }

    //MÉTODOS DE COMPORTAMIENTO

    // 1. Modifica velocidad y consume energía proporcionalmente
    public void acelerar(double incrementoVelocidad) {
        if (!estaActivo) {
            System.out.println(nombre + " está fuera de combate y no puede acelerar.");
            return;
        }
        this.velocidadActual += incrementoVelocidad;
        this.bateria.consumirEnergia(incrementoVelocidad * 0.5);
    }

    // 2. Reduce la batería y desactiva el robot si llega a 0
    public void recibirDano(double dano) {
        this.bateria.consumirEnergia(dano);
        if (this.bateria.getPorcentajeEnergia() == 0.0) {
            this.estaActivo = false;
            this.velocidadActual = 0.0;
            System.out.println(nombre + " ha quedado FUERA DE COMBATE.");
        }
    }

    // 3. Permite recargar el sistema de energía
    public void recargarBateria(double cantidad) {
        this.bateria.recargarEnergia(cantidad);
        if (this.bateria.getPorcentajeEnergia() > 0.0) {
            this.estaActivo = true;
        }
    }

    // 4. Calcula y retorna la potencia de ataque según velocidad y batería
    public double calcularPotenciaAtaque(double factorMasa) {
        if (!estaActivo) return 0.0;
        return (this.velocidadActual * factorMasa) * (this.bateria.getPorcentajeEnergia() / 100.0);
    }

    // Getters
    public UUID getId() { return id; }
    public String getNombre() { return nombre; }
    public boolean isEstaActivo() { return estaActivo; }

    // --- SOBRESCRITURA DE OBJECT ---

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Robot robot = (Robot) obj;
        return Objects.equals(id, robot.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Robot {" +
                "UUID=" + id +
                ", Nombre='" + nombre + '\'' +
                ", Categ='" + categoria + '\'' +
                ", Vel=" + String.format("%.1f", velocidadActual) + " m/s" +
                ", Activo=" + estaActivo +
                "\n  Propietario=" + propietario +
                "\n  Batería=" + bateria +
                '}';
    }
}
