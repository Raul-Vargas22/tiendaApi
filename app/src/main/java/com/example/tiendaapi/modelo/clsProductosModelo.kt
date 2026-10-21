package com.example.tiendaapi.modelo

class clsProductosModelo {

    suspend fun obtenerProductosPorCategoria(categoriaId: Int): List<dataProductos> {
        return RetrofitCliente.apiService.obtenerProductos(categoriaId)
    }

    suspend fun obtenerDetalleProducto(productoId: Int): List<dataImagenesProducto> {
        return RetrofitCliente.apiService.detalleProducto(productoId)
    }
}