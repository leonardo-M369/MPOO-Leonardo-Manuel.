package package mx.unam.fi.parkmanager;

public class BoletoPaseAnual extends Boleto {
    public BoletoPaseAnual() {
        super(2400.0);
    }

    @Override
    public String getTipo() {
        return "PASE_ANUAL";
    }
}