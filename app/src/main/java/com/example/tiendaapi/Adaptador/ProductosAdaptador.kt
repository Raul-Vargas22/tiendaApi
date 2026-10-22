package com.example.tiendaapi.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tiendaapi.R
import com.example.tiendaapi.modelo.dataProductos
import com.example.tiendaapi.vista.MainActivity

class ProductosAdaptador(
    private val activity: MainActivity,
    private var listaProductos: List<dataProductos>
) : RecyclerView.Adapter<ProductosAdaptador.ProductoViewHolder>() {

    class ProductoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.txtProducto)
        val tvPrecio: TextView = itemView.findViewById(R.id.txtPrecio)
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)

        return ProductoViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductoViewHolder, position: Int) {
        val producto = listaProductos[position]


        holder.tvNombre.text = producto.nombre
        holder.tvPrecio.text = "$${producto.precio}"

        val baseUrl = "http://10.226.139.102/tienda_api/"
        val rutaImagen = producto.urlimagenprincipal.trim().removePrefix("/")

        val urlImagenCompleta = if (rutaImagen.startsWith("http://") || rutaImagen.startsWith("https://")) {
            rutaImagen
        } else {
            baseUrl + rutaImagen
        }

        Glide.with(holder.itemView.context)
            .load(urlImagenCompleta)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.imgProducto)

        holder.itemView.setOnClickListener {
            activity.seleccionarProducto(producto)
        }
    }

    override fun getItemCount(): Int = listaProductos.size

    fun actualizarLista(nuevaLista: List<dataProductos>) {
        listaProductos = nuevaLista
        notifyDataSetChanged()
    }
}