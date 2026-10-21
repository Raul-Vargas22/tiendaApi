package com.example.tiendaapi.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    @SerialName("exito") val exito: Boolean = false,
    @SerialName("mensaje") val mensaje: String = "",
    @SerialName("idUsuario") val idUsuario: Int = 0,
    @SerialName("nombreUsuario") val nombreUsuario: String = "",
    @SerialName("nombreCliente") val nombreCliente: String = "",
    @SerialName("telefono") val telefono: String = ""
)