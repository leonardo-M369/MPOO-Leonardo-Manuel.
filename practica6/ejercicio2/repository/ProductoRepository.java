package practica6.ejercicio2.repository;

import practica6.ejercicio2.domain.Producto;
import practica6.ejercicio2.dto.ProductoDTO;

public interface ProductoRepository {
    boolean guardar(ProductoDTO producto);
    Producto buscarPorId(int idProducto);
    boolean existe(int idProducto);
}