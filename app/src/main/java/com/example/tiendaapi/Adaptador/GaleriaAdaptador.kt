package com.example.tiendaapi.Adaptador

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tiendaapi.R
import com.example.tiendaapi.modelo.dataImagenesProducto
import com.bumptech.glide.Glide

class GaleriaAdaptador(
    private var listaImagenes: List<dataImagenesProducto>) : RecyclerView.Adapter<GaleriaAdaptador.GaleriaViewHolder>() {

    class GaleriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProducto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GaleriaViewHolder {
        val vista = LayoutInflater.from(parent.context).inflate(R.layout.item_galeria, parent, false)
        return GaleriaViewHolder(vista)
    }

    override fun onBindViewHolder(holder: GaleriaViewHolder, position: Int) {
        val imagen = listaImagenes[position]

        Glide.with(holder.itemView.context)
            .load(imagen.urlImagen)
            .placeholder(android.R.drawable.ic_menu_gallery)
            .error(android.R.drawable.ic_menu_report_image)
            .into(holder.imgProducto)
    }

    override fun getItemCount(): Int = listaImagenes.size

    fun actualizarLista(nuevaLista: List<dataImagenesProducto>) {
        listaImagenes = nuevaLista
        notifyDataSetChanged()
    }
}