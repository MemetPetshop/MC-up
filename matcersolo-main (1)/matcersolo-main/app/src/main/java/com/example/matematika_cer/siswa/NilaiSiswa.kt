package com.example.matematika_cer.siswa

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.matematika_cer.R


class NilaiSiswaFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: NilaiSiswaAdapter
    private lateinit var nilaiList: List<NilaiSiswaModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_nilai_siswa, container, false)

        recyclerView = view.findViewById(R.id.rvNilaiSiswa)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Data dummy topik kuis
        nilaiList = listOf(
            NilaiSiswaModel("Deret angka", "25 Maret 2025"),
            NilaiSiswaModel("Perkalian", "2 Maret 2025"),
            NilaiSiswaModel("Pembagian", "12 Februari 2025"),
            NilaiSiswaModel("Penjumlahan", "6 Januari 2025")
        )

        adapter = NilaiSiswaAdapter(nilaiList) { selected ->
            // Klik item → buka DetailNilaiSiswaFragment
            val bundle = Bundle().apply {
                putString("topik", selected.namaTopik)
                putString("tanggal", selected.tanggal)
            }
            findNavController().navigate(R.id.action_nilaiSiswa_to_detailNilaiSiswa, bundle)
        }

        recyclerView.adapter = adapter

        // 🔍 Search logic
        val searchEditText = view.findViewById<EditText>(R.id.searchTopikEditText)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filtered = nilaiList.filter {
                    it.namaTopik.contains(s.toString(), ignoreCase = true)
                }
                adapter.updateData(filtered)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }
}
