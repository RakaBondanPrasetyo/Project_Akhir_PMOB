package com.raka.projectakhir

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raka.projectakhir.databinding.ItemPesananBinding

class PesanAdapter(private val pesanan: List<Pesan>) :
    RecyclerView.Adapter<PesanAdapter.PesanViewHolder>() {

    class PesanViewHolder(private val binding: ItemPesananBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pesan: Pesan){
            binding.tvNamaPesanan.text = pesan.name
            binding.tvNoHp.text = pesan.phone
            binding.tvEmail.text = pesan.email
            binding.tvTgglMenempati.text = pesan.releaseDate
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PesanViewHolder {
        val binding = ItemPesananBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PesanViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PesanViewHolder, position: Int) {
        val pesan = pesanan[position]
        holder.bind(pesan)
    }

    override fun getItemCount() = pesanan.size
}