package com.example.tiendaapi.presentador

import com.example.tiendaapi.modelo.dataCategorias
import com.example.tiendaapi.vista.MainActivity
import com.example.tiendaapi.modelo.clsCategoriasModelo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class clsCategoriasPresentador(private val vista: MainActivity) {
    private val modelo = clsCategoriasModelo()
    private var listaCategorias: List<dataCategorias> = emptyList()

    fun iniciar() {
        CoroutineScope(Dispatchers.Main).launch {
            try {
                listaCategorias = modelo.obtenerCategorias()
                vista.mostrarCategorias(listaCategorias)
            } catch (e: Exception) {
                vista.mostrarError("Error: ${e.localizedMessage}")
            }
        }
    }

    fun seleccionarCategoria(posicion: Int) {
        if (posicion >= 0 && posicion < listaCategorias.size) {
            val categoria = listaCategorias[posicion]
            vista.mostrarMensajeExito(categoria.id, categoria.categoria)
        }
    }

}