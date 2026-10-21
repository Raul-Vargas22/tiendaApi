package com.example.tiendaapi.presentador

import com.example.tiendaapi.modelo.clsCategoriasModelo
import com.example.tiendaapi.modelo.clsProductosModelo
import com.example.tiendaapi.modelo.dataCategorias
import com.example.tiendaapi.modelo.dataProductos
import com.example.tiendaapi.vista.MainActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsCategoriasPresentador(private val vista: MainActivity) {

    private val modeloCategorias = clsCategoriasModelo()
    private val modeloProductos = clsProductosModelo()

    private var listaCategorias: List<dataCategorias> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCategorias = modeloCategorias.obtenerCategorias()
                vista.mostrarCategorias(listaCategorias)
                seleccionarCategoria(0)
            } catch (e: Exception) {
                vista.mostrarError(
                    "Error al cargar categorías: ${e.message ?: "Error desconocido"}"
                )
            }
        }
    }

    fun seleccionarCategoria(posicion: Int) {
        if (posicion >= 0 && posicion < listaCategorias.size) {
            val categoria = listaCategorias[posicion]
            cargarProductos(categoria.id)
        }
    }

    private fun cargarProductos(categoriaId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val productos =
                    modeloProductos.obtenerProductosPorCategoria(categoriaId)

                vista.mostrarProductos(productos)
            } catch (e: Exception) {
                vista.mostrarError(
                    "Error al cargar productos: ${e.message ?: "Error desconocido"}"
                )
            }
        }
    }

    fun seleccionarProducto(producto: dataProductos) {
        val precioDouble = producto.precio.toDoubleOrNull() ?: 0.0

        vista.abrirDetalleProducto(
            producto.id,
            producto.nombre,
            producto.descripcion,
            precioDouble
        )
    }
}