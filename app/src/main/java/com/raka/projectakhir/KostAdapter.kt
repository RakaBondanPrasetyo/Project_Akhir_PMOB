package com.raka.projectakhir

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.filter
import kotlin.collections.toList
import kotlin.text.contains
import kotlin.text.isEmpty
import kotlin.text.lowercase

class KostAdapter(
    private var listKost: List<Kost>,
    private val onItemClick: (Kost) -> Unit
) : RecyclerView.Adapter<KostAdapter.KostViewHolder>() {

    private val originalList: List<Kost> = listKost.toList()

    inner class KostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.tvNamaKost)
        val rating: TextView = itemView.findViewById(R.id.tvRatingKost)
        val jarak: TextView = itemView.findViewById(R.id.tvJarakost)
        val deskripsi: TextView = itemView.findViewById(R.id.tvDesc)
        val harga: TextView = itemView.findViewById(R.id.tvHarga)
        val ivFoto: ImageView = itemView.findViewById(R.id.imageKost)
//        val noHpPemilik: TextView = itemView.findViewById(R.id.tvNoPemilikKost)
//        val namaPemilik: TextView = itemView.findViewById(R.id.tvPemilikKostDetail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KostViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_kost, parent, false)
        return KostViewHolder(view)
    }

    override fun onBindViewHolder(holder: KostViewHolder, position: Int) {
        val kost = listKost[position]

        holder.nama.text = kost.nama
        holder.rating.text = kost.rating
        holder.jarak.text = kost.jarak
        holder.deskripsi.text = kost.desckripsi
        holder.harga.text = kost.harga
        holder.ivFoto.setImageResource(kost.fotoResId)

        holder.itemView.setOnClickListener {
            onItemClick(kost)
        }
    }

    override fun getItemCount(): Int = listKost.size

}

