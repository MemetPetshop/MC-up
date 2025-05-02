package com.example.matematika_cer.siswa

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matematika_cer.R

class BerandaSiswaFragment : Fragment() {

    private lateinit var topikAdapter: TopikSiswaAdapter
    private lateinit var topikList: List<TopikSiswaModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beranda_siswa, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tampilkanNilaiTerbaru(view)
        setupDaftarTopik(view)
    }

    private fun tampilkanNilaiTerbaru(view: View) {
        val prefs = requireContext().getSharedPreferences("nilai_pref", Context.MODE_PRIVATE)
        val topik = prefs.getString("topik_terakhir", "Belum ada") ?: "Belum ada"
        val nilai = prefs.getInt("nilai_terakhir", 0)
        val total = prefs.getInt("total_soal", 100)

        val textTopik = view.findViewById<TextView>(R.id.text_topik_nilai)
        val textNilai = view.findViewById<TextView>(R.id.text_nilai)

        textTopik.text = topik
        textNilai.text = "Nilai : $nilai/$total"
    }

    private fun setupDaftarTopik(view: View) {
        // Dummy data lengkap
        topikList = listOf(
            TopikSiswaModel(1, "Penjumlahan dan Pengurangan", "Topik dasar aritmetika", "4", "Ibu Sali", 10, "05:00"),
            TopikSiswaModel(2, "Perkalian dan Pembagian", "Latihan dasar perkalian", "4", "Ibu Rani", 8, "04:00"),
            TopikSiswaModel(3, "Pecahan", "Memahami pecahan dalam matematika", "4", "Pak Budi", 12, "06:00"),
            TopikSiswaModel(4, "Pengukuran", "Satuan panjang dan berat", "4", "Ibu Lilis", 9, "05:00")
        )

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvTopik)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        topikAdapter = TopikSiswaAdapter(topikList) { topik ->
            val bundle = Bundle().apply {
                putString("namaTopik", topik.namaTopik)
                putString("deskripsi", topik.deskripsi)
                putString("kelas", topik.kelas)
                putString("pembuat", topik.pembuat)
                putInt("jumlahSoal", topik.jumlahSoal)
                putString("durasi", topik.durasi)
            }
            findNavController().navigate(
                R.id.action_berandaSiswaFragment_to_detailTopikSiswaFragment,
                bundle
            )
        }
        recyclerView.adapter = topikAdapter

        val searchBar = view.findViewById<EditText>(R.id.searchBar)
        searchBar.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val keyword = s.toString().lowercase()
                val filtered = topikList.filter {
                    it.namaTopik.lowercase().contains(keyword)
                }
                topikAdapter.filterList(filtered)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }
}
