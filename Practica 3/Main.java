import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CuentaBancariaService service = new CuentaBancariaService();
        int opcion = 0;

        while (opcion != 4) {
            service.mostrarEstadoCuentas();
            System.out.println("========== SISTEMA BANCARIO ==========");
            System.out.println("1. Transferir dinero");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("\nSeleccione una opción: ");

            if (!scanner.hasNextInt()) {
                System.out.println("Opción inválida.");
                scanner.next();
                continue;
            }

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Cuenta origen: ");
                    String orig = scanner.nextLine();
                    System.out.print("Cuenta destino: ");
                    String dest = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    double cantT = scanner.nextDouble();
                    if (service.transferir(orig, dest, cantT)) {
                        System.out.println("Transferencia exitosa.");
                    } else {
                        System.out.println("Error en la transferencia.");
                    }
                    break;
                case 2:
                    System.out.print("Cuenta destino: ");
                    String destD = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    double cantD = scanner.nextDouble();
                    if (service.depositar(destD, cantD)) {
                        System.out.println("Depósito exitoso.");
                    } else {
                        System.out.println("Error en el depósito.");
                    }
                    break;
                case 3:
                    System.out.print("Cuenta origen: ");
                    String origR = scanner.nextLine();
                    System.out.print("Cantidad: ");
                    double cantR = scanner.nextDouble();
                    if (service.retirar(origR, cantR)) {
                        System.out.println("Retiro exitoso.");
                    } else {
                        System.out.println("Error en el retiro.");
                    }
                    break;
                case 4:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}