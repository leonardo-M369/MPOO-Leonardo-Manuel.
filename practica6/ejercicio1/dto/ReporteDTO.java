package practica6.ejercicio1.dto;

public final class ReporteDTO {
    private final int registrados;
    private final int disponibles;
    private final int aforo;
    private final int alumnos;
    private final int profesores;
    private final int invitados;
    private final double ocupacion;

    public ReporteDTO(int registrados, int disponibles, int aforo, int alumnos, int profesores, int invitados, double ocupacion) {
        this.registrados = registrados;
        this.disponibles = disponibles;
        this.aforo = aforo;
        this.alumnos = alumnos;
        this.profesores = profesores;
        this.invitados = invitados;
        this.ocupacion = ocupacion;
    }

    public int getRegistrados() { return registrados; }
    public int getDisponibles() { return disponibles; }
    public int getAforo() { return aforo; }
    public int getAlumnos() { return alumnos; }
    public int getProfesores() { return profesores; }
    public int getInvitados() { return invitados; }
    public double getOcupacion() { return ocupacion; }
}
