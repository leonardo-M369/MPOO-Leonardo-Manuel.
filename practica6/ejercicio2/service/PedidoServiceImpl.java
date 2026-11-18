package practica6.ejercicio2.service;

import practica6.ejercicio2.domain.ElementoPedido;
import practica6.ejercicio2.domain.Pedido;
import practica6.ejercicio2.domain.Producto;
import practica6.ejercicio2.dto.*;
import practica6.ejercicio2.repository.ProductoRepository;
import practica6.ejercicio2.strategy.*;

import java.util.ArrayList;
import java.util.List;

public class PedidoServiceImpl implements PedidoService {
    private final ProductoRepository productoRepository;

    public PedidoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public EstadoAgregarProducto agregarProducto(Pedido pedido, int idProducto, int cantidad) {
        if (cantidad <= 0) {
            return EstadoAgregarProducto.CANTIDAD_INVALIDA;
        }

        if (!productoRepository.existe(idProducto)) {
            return EstadoAgregarProducto.PRODUCTO_NO_EXISTE;
        }

        Producto producto = productoRepository.buscarPorId(idProducto);
        pedido.agregarOActualizarProducto(producto, cantidad);

        return EstadoAgregarProducto.AGREGADO;
    }

    @Override
    public DescuentoStrategy SelectorDescuento(TipoDescuento tipoDescuento) {
        if (tipoDescuento == null) {
            return new DescuentoRegular();
        }

        switch (tipoDescuento) {
            case FRECUENTE:
                return new DescuentoFrecuente();
            case MAYOREO:
                return new DescuentoMayoreo();
            case REGULAR:
            default:
                return new DescuentoRegular();
        }
    }

    @Override
    public ResumenPedidoDTO confirmarPedido(Pedido pedido, TipoDescuento tipoDescuento) {
        DescuentoStrategy estrategia = SelectorDescuento(tipoDescuento);
        pedido.setDiscount(estrategia);

        double subtotal = pedido.calcularSubtotal();
        double descuento = pedido.calcularDescuento();
        double total = pedido.calcularTotal();

        List<DetallePedidoDTO> detalles = new ArrayList<>();
        for (ElementoPedido elem : pedido.getElementos()) {
            detalles.add(new DetallePedidoDTO(
                    elem.getProducto().getId(),
                    elem.getProducto().getNombre(),
                    elem.getCantidad()
            ));
        }

        return new ResumenPedidoDTO(detalles, subtotal, descuento, total);
    }
}