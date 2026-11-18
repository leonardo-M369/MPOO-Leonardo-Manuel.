smartvend/
├── pom.xml
└── src
    └── main
        └── java
            └── mx
                └── unam
                    └── fi
                        └── smartvend
                            ├── SmartVendApplication.java
                            ├── api
                            │   ├── controller
                            │   │   ├── CompraController.java
                            │   │   ├── FondosController.java
                            │   │   └── ProductoController.java
                            │   └── dto
                            │       ├── CompraRequestDto.java
                            │       ├── ExistenciasDto.java
                            │       └── ProductoRegistroDto.java
                            ├── application
                            │   └── service
                            │       └── SmartVendService.java
                            ├── config
                            │   └── MonederoConfig.java
                            ├── domain
                            │   ├── compra
                            │   │   └── ResultadoCobro.java
                            │   ├── inventario
                            │   │   ├── Inventario.java
                            │   │   └── ProductoInventario.java
                            │   └── pago
                            │       ├── EstadoCobro.java
                            │       ├── MetodoPago.java
                            │       ├── Monedero.java
                            │       ├── MonederoEfectivo.java
                            │       └── MonederoTarjeta.java
                            │   └── producto
                            │       ├── CategoriaProducto.java
                            │       └── Producto.java
                            └── infrastructure
                                └── inventario
                                    └── InventarioMemoriaRepository.java