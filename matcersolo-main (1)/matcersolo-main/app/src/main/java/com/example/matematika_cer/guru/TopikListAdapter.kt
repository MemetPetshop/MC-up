package com.example.matematika_cer.guru

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matematika_cer.R

class TopikListAdapter(private var list: List<TopikModel>) :
    RecyclerView.Adapter<TopikListAdapter.TopikViewHolder>() {

    inner class TopikViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nama: TextView = itemView.findViewById(R.id.nama_topik)
        val durasi: TextView = itemView.findViewById(R.id.durasi)
        val total: TextView = itemView.findViewById(R.id.total_soal)
        val peserta: TextView = itemView.findViewById(R.id.peserta)
        val tanggal: TextView = itemView.findViewById(R.id.tanggal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopikViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_topik_card, parent, false)
        return TopikViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopikViewHolder, position: Int) {
        val topik = list[position]
        holder.nama.text = topik.namaTopik
        holder.durasi.text = "Durasi: ${topik.durasi}"
        holder.total.text = "Jumlah soal: ${topik.jumlahSoal}"
        holder.peserta.text = "${topik.jumlahMenjawab}/${topik.totalPeserta}"
        holder.tanggal.text = "Tanggal: ${topik.tanggal}"
    }

    override fun getItemCount(): Int = list.size

    // ✅ Tambahan untuk search
    fun updateData(newList: List<TopikModel>) {
        list = newList
        notifyDataSetChanged()
    }
}
