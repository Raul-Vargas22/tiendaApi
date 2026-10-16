package com.example.tiendaapi.interfaceApi

import com.example.tiendaapi.modelo.dataCategorias
import retrofit2.http.GET

interface ApiService {
    @GET("obtener_categorias.php") //Este es el api en php
    suspend fun obtenerCategorias(): List<dataCategorias>
}