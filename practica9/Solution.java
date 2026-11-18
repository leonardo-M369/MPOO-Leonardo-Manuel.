package practica9;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/* =========================================================
   ENUMS PROPORCIONADOS POR HACKERRANK
   ========================================================= */

enum CategoriaProducto {
    BEBIDA,
    SNACK
}

enum ResultadoRegistro {
    PRODUCTO_REGISTRADO,
    CODIGO_DUPLICADO,
    PRODUCTO_INVALIDO,
    PRECIO_INVALIDO
}

enum ResultadoReabastecimiento {
    REABASTECIMIENTO_REALIZADO,
    PRODUCTO_NO_ENCONTRADO,
    CANTIDAD_INVALIDA
}

enum ResultadoEntrega {
    PRODUCTO_PREPARADO,
    PRODUCTO_NO_ENCONTRADO,
    SIN_EXISTENCIAS
}

enum EstadoCobro {
    COBRO_REALIZADO,
    MONTO_INVALIDO,
    SIN_FONDOS_SUFICIENTES
}

enum EstadoCompra {
    VENTA_REALIZADA,
    PRODUCTO_NO_ENCONTRADO,
    SIN_EXISTENCIAS,
    PAGO_INSUFICIENTE,
    SIN_FONDOS_SUFICIENTES
}

/* =========================================================
   CLASES DE RESULTADO Y TRANSFERENCIA
   ========================================================= */

class ProductoInventario {
    private final Producto producto;
    private final double precio;
    private final int existencias;

    public ProductoInventario(Producto producto, double precio, int existencias) {
        this.producto = producto;
        this.precio = precio;
        this.existencias = existencias;
    }

    public Producto getProducto() { return producto; }
    public double getPrecio() { return precio; }
    public int getExistencias() { return existencias; }
}

class ResultadoCobro {
    private final EstadoCobro estado;
    private final double cambio;

    public ResultadoCobro(EstadoCobro estado, double cambio) {
        this.estado = estado;
        this.cambio = cambio;
    }

    public EstadoCobro getEstado() { return estado; }
    public double getCambio() { return cambio; }
}

class ResultadoCompra {
    private final EstadoCompra estado;
    private final Producto producto;
    private final double cambio;

    public ResultadoCompra(EstadoCompra estado, Producto producto, double cambio) {
        this.estado = estado;
        this.producto = producto;
        this.cambio = cambio;
    }

    public EstadoCompra getEstado() { return estado; }
    public Producto getProducto() { return producto; }
    public double getCambio() { return cambio; }
}

/* =========================================================
   DTOs DE ENTRADA
   ========================================================= */

class RegistroProductoDTO {
    private final String codigo;
    private final String nombre;
    private final LocalDate fechaCaducidad;
    private final CategoriaProducto categoria;
    private final double precio;

    public RegistroProductoDTO(String codigo, String nombre, LocalDate fechaCaducidad, CategoriaProducto categoria, double precio) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
        this.categoria = categoria;
        this.precio = precio;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public CategoriaProducto getCategoria() { return categoria; }
    public double getPrecio() { return precio; }
}

class ReabastecimientoDTO {
    private final String codigo;
    private final int cantidad;

    public ReabastecimientoDTO(String codigo, int cantidad) {
        this.codigo = codigo;
        this.cantidad = cantidad;
    }

    public String getCodigo() { return codigo; }
    public int getCantidad() { return cantidad; }
}

class CompraDTO {
    private final String codigoProducto;
    private final double cantidadRecibida;

    public CompraDTO(String codigoProducto, double cantidadRecibida) {
        this.codigoProducto = codigoProducto;
        this.cantidadRecibida = cantidadRecibida;
    }

    public String getCodigoProducto() { return codigoProducto; }
    public double getCantidadRecibida() { return cantidadRecibida; }
}

/* =========================================================
   INTERFACES
   ========================================================= */

interface Inventario {
    ResultadoRegistro registrarProducto(Producto producto, double precio);
    Optional<ProductoInventario> buscarProducto(String codigo);
    ResultadoReabastecimiento reabastecer(String codigo, int cantidad);
    ResultadoEntrega despachar(String codigo);
}

interface Monedero {
    ResultadoCobro cobrar(double precio, double cantidadRecibida);
    double consultarFondos();
}

interface MaquinaDespachadora {
    ResultadoRegistro registrarProducto(RegistroProductoDTO producto);
    Optional<ProductoInventario> buscarProducto(String codigo);
    ResultadoReabastecimiento reabastecer(ReabastecimientoDTO solicitud);
    ResultadoCompra comprar(CompraDTO solicitud);
    double consultarFondos();
}

/* =========================================================
   DOMINIO Y LOGICA
   ========================================================= */

abstract class Producto {
    private final String codigo;
    private final String nombre;
    private final LocalDate fechaCaducidad;
    private final CategoriaProducto categoria;

    protected Producto(String codigo, String nombre, LocalDate fechaCaducidad, CategoriaProducto categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fechaCaducidad = fechaCaducidad;
        this.categoria = categoria;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public LocalDate getFechaCaducidad() { return fechaCaducidad; }
    public CategoriaProducto getCategoria() { return categoria; }
}

class Bebida extends Producto {
    protected Bebida(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.BEBIDA);
    }
}

class Snack extends Producto {
    protected Snack(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad, CategoriaProducto.SNACK);
    }
}

class Refresco extends Bebida {
    public Refresco(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad);
    }
}

class PapaFrita extends Snack {
    public PapaFrita(String codigo, String nombre, LocalDate fechaCaducidad) {
        super(codigo, nombre, fechaCaducidad);
    }
}

class ItemInventario {
    private final Producto producto;
    private double precio;
    private int existencias;

    public ItemInventario(Producto producto, double precio, int existencias) {
        this.producto = producto;
        this.precio = precio;
        this.existencias = existencias;
    }

    public Producto getProducto() { return producto; }
    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }
    public int getExistencias() { return existencias; }
    public void setExistencias(int existencias) { this.existencias = existencias; }
}

class InventarioImpl implements Inventario {
    private final Map<String, ItemInventario> almacen = new HashMap<>();

    @Override
    public ResultadoRegistro registrarProducto(Producto producto, double precio) {
        if (producto == null || producto.getCodigo() == null || producto.getCodigo().trim().isEmpty()) {
            return ResultadoRegistro.PRODUCTO_INVALIDO;
        }
        if (precio <= 0) {
            return ResultadoRegistro.PRECIO_INVALIDO;
        }
        if (almacen.containsKey(producto.getCodigo())) {
            return ResultadoRegistro.CODIGO_DUPLICADO;
        }
        almacen.put(producto.getCodigo(), new ItemInventario(producto, precio, 0));
        return ResultadoRegistro.PRODUCTO_REGISTRADO;
    }

    @Override
    public Optional<ProductoInventario> buscarProducto(String codigo) {
        if (codigo == null) return Optional.empty();
        ItemInventario item = almacen.get(codigo);
        if (item == null) return Optional.empty();
        return Optional.of(new ProductoInventario(item.getProducto(), item.getPrecio(), item.getExistencias()));
    }

    @Override
    public ResultadoReabastecimiento reabastecer(String codigo, int cantidad) {
        if (cantidad <= 0) return ResultadoReabastecimiento.CANTIDAD_INVALIDA;
        ItemInventario item = almacen.get(codigo);
        if (item == null) return ResultadoReabastecimiento.PRODUCTO_NO_ENCONTRADO;
        item.setExistencias(item.getExistencias() + cantidad);
        return ResultadoReabastecimiento.REABASTECIMIENTO_REALIZADO;
    }

    @Override
    public ResultadoEntrega despachar(String codigo) {
        ItemInventario item = almacen.get(codigo);
        if (item == null) return ResultadoEntrega.PRODUCTO_NO_ENCONTRADO;
        if (item.getExistencias() <= 0) return ResultadoEntrega.SIN_EXISTENCIAS;
        item.setExistencias(item.getExistencias() - 1);
        return ResultadoEntrega.PRODUCTO_PREPARADO;
    }
}

class MonederoEfectivo implements Monedero {
    private double fondos;

    public MonederoEfectivo(double fondosIniciales) {
        if (fondosIniciales < 0) {
            throw new IllegalArgumentException("Los fondos no pueden ser negativos.");
        }
        this.fondos = fondosIniciales;
    }

    @Override
    public ResultadoCobro cobrar(double precio, double cantidadRecibida) {
        if (precio <= 0 || cantidadRecibida < precio) {
            return new ResultadoCobro(EstadoCobro.MONTO_INVALIDO, 0.0);
        }
        double cambio = cantidadRecibida - precio;
        if (fondos < cambio) {
            return new ResultadoCobro(EstadoCobro.SIN_FONDOS_SUFICIENTES, 0.0);
        }
        fondos += precio;
        return new ResultadoCobro(EstadoCobro.COBRO_REALIZADO, cambio);
    }

    @Override
    public double consultarFondos() {
        return fondos;
    }
}

class MaquinaExpendedora implements MaquinaDespachadora {
    private final Inventario inventario;
    private final Monedero monedero;

    private MaquinaExpendedora(Builder builder) {
        this.inventario = builder.inventario;
        this.monedero = builder.monedero;
    }

    public static Builder builder() { return new Builder(); }

    @Override
    public ResultadoRegistro registrarProducto(RegistroProductoDTO dto) {
        if (dto == null) return ResultadoRegistro.PRODUCTO_INVALIDO;
        Producto producto = (dto.getCategoria() == CategoriaProducto.BEBIDA)
            ? new Refresco(dto.getCodigo(), dto.getNombre(), dto.getFechaCaducidad())
            : new PapaFrita(dto.getCodigo(), dto.getNombre(), dto.getFechaCaducidad());
        return inventario.registrarProducto(producto, dto.getPrecio());
    }

    @Override
    public Optional<ProductoInventario> buscarProducto(String codigo) {
        return inventario.buscarProducto(codigo);
    }

    @Override
    public ResultadoReabastecimiento reabastecer(ReabastecimientoDTO solicitud) {
        if (solicitud == null) return ResultadoReabastecimiento.CANTIDAD_INVALIDA;
        return inventario.reabastecer(solicitud.getCodigo(), solicitud.getCantidad());
    }

    @Override
    public ResultadoCompra comprar(CompraDTO solicitud) {
        if (solicitud == null) return new ResultadoCompra(EstadoCompra.PRODUCTO_NO_ENCONTRADO, null, 0.0);

        Optional<ProductoInventario> optProd = inventario.buscarProducto(solicitud.getCodigoProducto());
        if (optProd.isEmpty()) return new ResultadoCompra(EstadoCompra.PRODUCTO_NO_ENCONTRADO, null, 0.0);

        ProductoInventario prodInv = optProd.get();
        if (prodInv.getExistencias() <= 0) return new ResultadoCompra(EstadoCompra.SIN_EXISTENCIAS, prodInv.getProducto(), 0.0);
        if (solicitud.getCantidadRecibida() < prodInv.getPrecio()) return new ResultadoCompra(EstadoCompra.PAGO_INSUFICIENTE, prodInv.getProducto(), 0.0);

        ResultadoCobro resultadoCobro = monedero.cobrar(prodInv.getPrecio(), solicitud.getCantidadRecibida());
        if (resultadoCobro.getEstado() == EstadoCobro.SIN_FONDOS_SUFICIENTES) {
            return new ResultadoCompra(EstadoCompra.SIN_FONDOS_SUFICIENTES, prodInv.getProducto(), 0.0);
        }
        if (resultadoCobro.getEstado() == EstadoCobro.MONTO_INVALIDO) {
            return new ResultadoCompra(EstadoCompra.PAGO_INSUFICIENTE, prodInv.getProducto(), 0.0);
        }

        inventario.despachar(solicitud.getCodigoProducto());
        return new ResultadoCompra(EstadoCompra.VENTA_REALIZADA, prodInv.getProducto(), resultadoCobro.getCambio());
    }

    @Override
    public double consultarFondos() {
        return monedero.consultarFondos();
    }

    public static class Builder {
        private Inventario inventario;
        private Monedero monedero;

        public Builder inventario(Inventario inventario) { this.inventario = inventario; return this; }
        public Builder monedero(Monedero monedero) { this.monedero = monedero; return this; }

        public MaquinaExpendedora build() {
            if (inventario == null || monedero == null) {
                throw new IllegalStateException("Se requieren Inventario y Monedero.");
            }
            return new MaquinaExpendedora(this);
        }
    }
}

class Result {
    public static MaquinaDespachadora crearMaquina(double fondosIniciales) {
        Inventario inventario = new InventarioImpl();
        Monedero monedero = new MonederoEfectivo(fondosIniciales);
        return MaquinaExpendedora.builder().inventario(inventario).monedero(monedero).build();
    }
}

public class Solution {
    public static void main(String[] args) {
        System.out.println("SmartVend inicializado correctamente en VS Code.");
    }
}