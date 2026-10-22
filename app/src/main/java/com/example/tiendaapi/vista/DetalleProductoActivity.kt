package com.example.tiendaapi.vista

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.tiendaapi.Adaptador.GaleriaAdaptador
import com.example.tiendaapi.R
import com.example.tiendaapi.modelo.dataImagenesProducto
import com.example.tiendaapi.presentador.clsDetalleProductoPresentador

class DetalleProductoActivity : AppCompatActivity() {

    private lateinit var presenter: clsDetalleProductoPresentador
    private lateinit var vpGaleria: ViewPager2
    private lateinit var txtPrecio: TextView
    private lateinit var txtNombre: TextView
    private lateinit var txtDescripcion: TextView
    private lateinit var adapterGaleria: GaleriaAdaptador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detalle_producto)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(
                systemBars.left,
                systemBars.top,
                systemBars.right,
                systemBars.bottom
            )
            insets
        }

        vpGaleria = findViewById(R.id.vpGaleria)
        txtPrecio = findViewById(R.id.txtPrecio)
        txtNombre = findViewById(R.id.txtDetalleProducto)
        txtDescripcion = findViewById(R.id.txtDetalleDescripcion)

        adapterGaleria = GaleriaAdaptador(emptyList())
        vpGaleria.adapter = adapterGaleria

        val id = intent.getIntExtra("PRODUCTO_ID", 0)
        val nombre = intent.getStringExtra("NOMBRE_PRODUCTO") ?: ""
        val descripcion = intent.getStringExtra("DESCRIPCION_PRODUCTO") ?: "No llegó"
        val precio = intent.getDoubleExtra("PRECIO_PRODUCTO", 0.0)

        txtNombre.text = nombre
        txtPrecio.text = "$precio"
        txtDescripcion.text = descripcion

        presenter = clsDetalleProductoPresentador(this)

        if (id != 0) {
            presenter.cargarDetalle(id)
        } else {
            mostrarError("No se recibió un ID de producto válido")
        }
    }

    fun mostrarImagenes(imagenes: List<dataImagenesProducto>) {
        adapterGaleria.actualizarLista(imagenes)
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}