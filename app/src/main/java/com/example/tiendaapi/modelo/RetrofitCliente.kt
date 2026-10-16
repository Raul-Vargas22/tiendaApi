package com.example.tiendaapi.modelo
import com.example.tiendaapi.interfaceApi.ApiService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlin.lazy

object RetrofitCliente {
    private const val BASE_URL = "http://172.20.10.2/tienda_api/" //aqui va la url del api

    private val jsonConfig = Json {
        ignoreUnknownKeys=true
        coerceInputValues=true
        isLenient=true
    }

    val apiService: ApiService by lazy {
        val contentType="application/json".toMediaType()
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(jsonConfig.asConverterFactory(contentType))
            .build()
            .create(ApiService::class.java)
    }
}