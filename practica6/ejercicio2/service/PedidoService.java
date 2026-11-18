package practica6.ejercicio2.service;

import practica6.ejercicio2.domain.Pedido;
import practica6.ejercicio2.dto.*;
import practica6.ejercicio2.strategy.DescuentoStrategy;

public interface PedidoService {
    EstadoAgregarProducto agregarProducto(Pedido pedido, int idProducto, int cantidad);
    DescuentoStrategy SelectorDescuento(TipoDescuento tipoDescuento);
    ResumenPedidoDTO confirmarPedido(Pedido pedido, TipoDescuento tipoDescuento);
}