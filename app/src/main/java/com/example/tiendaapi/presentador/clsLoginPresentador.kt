package com.example.tiendaapi.presentador

import com.example.tiendaapi.modelo.clsLoginModelo
import com.example.tiendaapi.vista.LoginActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class clsLoginPresentador(private val view: LoginActivity) {

    private val modelo = clsLoginModelo()

    fun realizarLogin(usuario: String, clave: String) {
        if (usuario.isEmpty() || clave.isEmpty()) {
            view.mostrarError("Por favor, ingresa usuario y contraseña")
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val respuesta = withContext(Dispatchers.IO) {
                    modelo.autenticarUsuario(usuario, clave)
                }
                if (respuesta.exito) {
                    view.loginExitoso(respuesta.mensaje, respuesta.nombreCliente)
                } else {
                    view.mostrarError(respuesta.mensaje)
                }
            } catch (e: Exception) {
                view.mostrarError("Error de conexión: ${e.localizedMessage ?: "Error desconocido"}")
            }
        }
    }
}
