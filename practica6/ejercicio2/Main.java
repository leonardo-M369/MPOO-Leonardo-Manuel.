package practica6.ejercicio2;

import practica6.ejercicio2.domain.Pedido;
import practica6.ejercicio2.dto.*;
import practica6.ejercicio2.repository.*;
import practica6.ejercicio2.service.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

public class Main {

    public static void main(String[] args) throws Exception {
        Locale.setDefault(Locale.US);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String productos = br.readLine();
        String operaciones = br.readLine();

        ProductoRepository productoRepository = new ProductoRepositoryImpl();
        PedidoService pedidoService = new PedidoServiceImpl(productoRepository);
        Pedido pedido = Pedido.empty();

        // 1. Carga de productos al catálogo
        if (productos != null && !productos.isBlank() && !productos.equals("-")) {
            String[] registros = productos.split(";");
            for (String registro : registros) {
                String[] datos = registro.split("\\|", 3);
                ProductoDTO productoDTO = new ProductoDTO(
                        datos[0].trim(),
                        datos[1].trim(),
                        datos[2].trim()
                );
                productoRepository.guardar(productoDTO);
            }
        }

        // 2. Procesamiento de operaciones del pedido
        StringBuilder salida = new StringBuilder();

        if (operaciones != null && !operaciones.isBlank() && !operaciones.equals("-")) {
            String[] listaOperaciones = operaciones.split(";");

            for (String operacion : listaOperaciones) {
                String[] datos = operacion.split("\\|");
                AccionPedido accion = AccionPedido.valueOf(datos[0].trim());

                if (accion == AccionPedido.AGREGAR) {
                    int idProducto = Integer.parseInt(datos[1].trim());
                    int cantidad = Integer.parseInt(datos[2].trim());

                    EstadoAgregarProducto estado = pedidoService.agregarProducto(
                            pedido, idProducto, cantidad
                    );

                    salida.append("AGREGAR ")
                            .append(idProducto)
                            .append(" ")
                            .append(estado)
                            .append("\n");

                } else if (accion == AccionPedido.CONFIRMAR) {
                    TipoDescuento tipoDescuento = TipoDescuento.valueOf(datos[1].trim());

                    ResumenPedidoDTO resumen = pedidoService.confirmarPedido(
                            pedido, tipoDescuento
                    );

                    if (resumen == null) {
                        throw new IllegalStateException("confirmarPedido no debe regresar null");
                    }

                    imprimirResumen(salida, resumen);
                }
            }
        }

        System.out.print(salida.toString());
    }

    private static void imprimirResumen(StringBuilder salida, ResumenPedidoDTO resumen) {
        List<DetallePedidoDTO> detalles = resumen.getDetalles();

        if (detalles == null) {
            throw new IllegalStateException("La lista de detalles no debe ser null");
        }

        for (DetallePedidoDTO detalle : detalles) {
            salida.append("ITEM ")
                    .append(detalle.getIdProducto())
                    .append(" ")
                    .append(detalle.getNombre())
                    .append(" ")
                    .append(detalle.getCantidad())
                    .append("\n");
        }

        salida.append(
                String.format(
                        Locale.US,
                        "RESUMEN %.2f %.2f %.2f",
                        resumen.getSubtotal(),
                        resumen.getDescuento(),
                        resumen.getTotal()
                )
        );
    }
}