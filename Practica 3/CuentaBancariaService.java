public class CuentaBancariaService {
    private CuentaBancaria[] cuentas;

    public CuentaBancariaService() {
        cuentas = new CuentaBancaria[] {
            new CuentaBancaria("1001", "Leonardo Silva", 5000.0, true),
            new CuentaBancaria("1002", "Maria Lopez", 3000.0, true),
            new CuentaBancaria("1003", "Carlos Gomez", 1500.0, false)
        };
    }

    private CuentaBancaria buscarCuenta(String numeroCuenta) {
        if (numeroCuenta == null) return null;
        for (CuentaBancaria cuenta : cuentas) {
            if (cuenta.getNumeroCuenta().equals(numeroCuenta)) {
                return cuenta;
            }
        }
        return null;
    }

    public boolean depositar(String cuentaDestino, double cantidad) {
        CuentaBancaria cuenta = buscarCuenta(cuentaDestino);
        if (cuenta == null) return false;
        return cuenta.depositar(cantidad);
    }

    public boolean retirar(String cuentaOrigen, double cantidad) {
        CuentaBancaria cuenta = buscarCuenta(cuentaOrigen);
        if (cuenta == null) return false;
        return cuenta.retirar(cantidad);
    }

    public boolean transferir(String cuentaOrigen, String cuentaDestino, double cantidad) {
        if (cuentaOrigen != null && cuentaOrigen.equals(cuentaDestino)) {
            return false;
        }
        CuentaBancaria origen = buscarCuenta(cuentaOrigen);
        CuentaBancaria destino = buscarCuenta(cuentaDestino);

        if (origen == null || destino == null) return false;
        if (!origen.retirar(cantidad)) return false;

        if (!destino.depositar(cantidad)) {
            origen.depositar(cantidad);
            return false;
        }

        return true;
    }

    public void mostrarEstadoCuentas() {
        System.out.println("\n--- ESTADO DE CUENTAS ---");
        for (CuentaBancaria c : cuentas) {
            System.out.println("Cuenta: " + c.getNumeroCuenta() + " | Titular: " + c.getTitular() + 
                               " | Saldo: $" + c.getSaldo() + " | Activa: " + c.isActiva());
        }
        System.out.println("-------------------------");
    }
}