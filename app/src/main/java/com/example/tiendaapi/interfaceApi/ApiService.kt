package com.example.tiendaapi.interfaceApi

import com.example.tiendaapi.modelo.LoginRequest
import com.example.tiendaapi.modelo.LoginResponse
import com.example.tiendaapi.modelo.dataCategorias
import com.example.tiendaapi.modelo.dataImagenesProducto
import com.example.tiendaapi.modelo.dataProductos
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("obtener_categorias.php")
    suspend fun obtenerCategorias(): List<dataCategorias>
    @GET("obtener_productos.php")
    suspend fun obtenerProductos(@Query(value="categoria_id")categoriaId: Int): List<dataProductos>
    @GET("detalle_producto.php")
    suspend fun detalleProducto(@Query(value="producto_id")productoId: Int): List<dataImagenesProducto>

    @POST("login.php")
    suspend fun iniciarSesion(@Body request: LoginRequest): LoginResponse
}
