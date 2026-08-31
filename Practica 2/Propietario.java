public class Propietario {
    private String nombre;
    private String curp;
    private String institucion;

    public Propietario(String nombre, String curp, String institucion) {
        this.nombre = nombre;
        this.curp = curp;
        this.institucion = institucion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCurp() {
        return curp;
    }

    public String getInstitucion() {
        return institucion;
    }

    @Override
    public String toString() {
        return nombre + " (CURP: " + curp + ", Inst: " + institucion + ")";
    }
}