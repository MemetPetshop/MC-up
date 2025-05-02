package com.example.matematika_cer.siswa

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.matematika_cer.R

class NilaiSiswaAdapter(
    private var listNilai: List<NilaiSiswaModel>,
    private val onItemClick: (NilaiSiswaModel) -> Unit
) : RecyclerView.Adapter<NilaiSiswaAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val namaTopik: TextView = itemView.findViewById(R.id.namaTopikTextView)
        val tanggal: TextView = itemView.findViewById(R.id.tanggalTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_nilai, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val nilai = listNilai[position]
        holder.namaTopik.text = nilai.namaTopik
        holder.tanggal.text = nilai.tanggal
        holder.itemView.setOnClickListener {
            onItemClick(nilai)
        }
    }

    override fun getItemCount(): Int = listNilai.size

    // ✅ Tambahkan fungsi untuk update list setelah filter/search
    fun updateData(newData: List<NilaiSiswaModel>) {
        listNilai = newData
        notifyDataSetChanged()
    }
}
