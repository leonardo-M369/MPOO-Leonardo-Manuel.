package practica6.ejercicio2.repository;

import practica6.ejercicio2.domain.Producto;
import practica6.ejercicio2.dto.ProductoDTO;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductoRepositoryImpl implements ProductoRepository {
    private final Map<Integer, Producto> productosMap;

    public ProductoRepositoryImpl() {
        this.productosMap = new LinkedHashMap<>();
    }

    @Override
    public boolean guardar(ProductoDTO productoDTO) {
        if (productoDTO == null || productoDTO.getId() == null) {
            return false;
        }
        try {
            int id = Integer.parseInt(productoDTO.getId().trim());
            String nombre = productoDTO.getNombre().trim();
            double precio = Double.parseDouble(productoDTO.getPrecio().trim());

            Producto producto = new Producto(id, nombre, precio);
            productosMap.put(id, producto);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Producto buscarPorId(int idProducto) {
        return productosMap.get(idProducto);
    }

    @Override
    public boolean existe(int idProducto) {
        return productosMap.containsKey(idProducto);
    }
}