package com.example.tiendaapi.presentador

import com.example.tiendaapi.modelo.clsProductosModelo
import com.example.tiendaapi.modelo.dataImagenesProducto
import com.example.tiendaapi.vista.DetalleProductoActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class clsDetalleProductoPresentador(private val vista: DetalleProductoActivity) {

    private val modelo = clsProductosModelo()

    fun cargarDetalle(productoId: Int) {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val listaImagenes: List<dataImagenesProducto> = modelo.obtenerDetalleProducto(productoId)

                vista.mostrarImagenes(listaImagenes)

            } catch (e: Exception) {
                vista.mostrarError("Error al cargar detalle: ${e.localizedMessage ?: "Error desconocido"}"
                )
            }
        }
    }
}