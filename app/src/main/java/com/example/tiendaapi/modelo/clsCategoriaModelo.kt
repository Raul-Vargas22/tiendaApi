package com.example.tiendaapi.modelo

import com.example.tiendaapi.modelo.dataCategorias

class clsCategoriasModelo {
    suspend fun obtenerCategorias(): List<dataCategorias> {

            val datos = RetrofitCliente.apiService.obtenerCategorias()

            val listaFinal = mutableListOf(dataCategorias(0, "Seleccione una categoría"))
            listaFinal.addAll(datos)

            return listaFinal
    }
}