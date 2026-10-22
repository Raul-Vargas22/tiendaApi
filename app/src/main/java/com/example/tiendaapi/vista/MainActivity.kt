package com.example.tiendaapi.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendaapi.Adaptador.ProductosAdaptador
import com.example.tiendaapi.R
import com.example.tiendaapi.modelo.dataCategorias
import com.example.tiendaapi.modelo.dataProductos
import com.example.tiendaapi.presentador.clsCategoriasPresentador

class MainActivity : AppCompatActivity() {

    private lateinit var presenter: clsCategoriasPresentador
    private lateinit var spnCategorias: Spinner
    private lateinit var recyclerProductos: RecyclerView
    private lateinit var adapterProductos: ProductosAdaptador
    private var primeraSeleccion = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

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

        spnCategorias = findViewById(R.id.spnCategorias)
        recyclerProductos = findViewById(R.id.rvLista)

        recyclerProductos.layoutManager = GridLayoutManager(this,2)
        adapterProductos = ProductosAdaptador(this, emptyList())
        recyclerProductos.adapter = adapterProductos

        presenter = clsCategoriasPresentador(this)

        configurarListeners()
        presenter.iniciar()
    }

    private fun configurarListeners() {
        spnCategorias.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    if (primeraSeleccion) {
                        primeraSeleccion = false
                        return
                    }
                    presenter.seleccionarCategoria(position)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    fun mostrarCategorias(categorias: List<dataCategorias>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias)

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spnCategorias.adapter = adapter
        primeraSeleccion = true
        spnCategorias.setSelection(0)
        presenter.seleccionarCategoria(0)
    }

    fun mostrarProductos(productos: List<dataProductos>) {
        adapterProductos.actualizarLista(productos)
    }

    fun seleccionarProducto(producto: dataProductos) {
        presenter.seleccionarProducto(producto)
    }

    fun abrirDetalleProducto(
        id: Int, nombre: String, descripcion: String, precio: Double) {
        val intent = Intent(this, DetalleProductoActivity::class.java)
        intent.putExtra("PRODUCTO_ID", id)
        intent.putExtra("NOMBRE_PRODUCTO", nombre)
        intent.putExtra("DESCRIPCION_PRODUCTO", descripcion)
        intent.putExtra("PRECIO_PRODUCTO", precio)
        startActivity(intent)
    }

    fun mostrarMensajeExito(id: Int, nombre: String) {
        Toast.makeText(this, "$id - Seleccionaste $nombre", Toast.LENGTH_SHORT).show()
    }

    fun mostrarError(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
    }
}