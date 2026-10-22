package com.example.tiendaapi.modelo

class clsCategoriasModelo {

    suspend fun obtenerCategorias(): List<dataCategorias> {
        val datos = RetrofitCliente.apiService.obtenerCategorias()

        val listaFinal = mutableListOf(dataCategorias(id = 0, categoria = "Todas las categorias"))

        listaFinal.addAll(datos)

        return listaFinal
    }
}