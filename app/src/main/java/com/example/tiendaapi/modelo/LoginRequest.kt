package com.example.tiendaapi.modelo

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    @SerialName("nombreUsuario") val nombreUsuario: String = "",
    @SerialName("passusuario") val passusuario: String = ""
)

