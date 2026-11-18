package mx.unam.fi.parkmanager;

public class BoletoGeneral extends Boleto {
    public BoletoGeneral() {
        super(900.0);
    }

    @Override
    public String getTipo() {
        return "GENERAL";
    }
}