package com.example.matematika_cer.guru

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.matematika_cer.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class BankSoalFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var tabDots: TabLayout
    private lateinit var edtCari: EditText

    private lateinit var semuaTopik: List<TopikModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_bank_soal, container, false)

        viewPager = view.findViewById(R.id.viewPagerBankSoal)
        tabDots = view.findViewById(R.id.tabDotsbanksoal)
        edtCari = view.findViewById(R.id.edtCari)

        // 🔰 Dummy data topik
        semuaTopik = listOf(
            TopikModel("Deret bilangan", "30 menit", 15, "25 Maret 2025", isAktif = true),
            TopikModel("Operasi Hitung", "20 menit", 10, "24 Maret 2025", isAktif = false),
            TopikModel("Pecahan", "25 menit", 12, "22 Maret 2025", isAktif = false),
            TopikModel("Bangun Datar", "35 menit", 20, "21 Maret 2025", isAktif = true),
            TopikModel("Kelipatan & Faktor", "40 menit", 25, "20 Maret 2025", isAktif = false),
            TopikModel("Volume Bangun", "45 menit", 30, "19 Maret 2025", isAktif = true)
        )

        // ⬇️ Tampilkan semua saat awal
        tampilkanTopikKeViewPager(semuaTopik)

        // 🔍 Fitur Search
        edtCari.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val hasilFilter = semuaTopik.filter {
                    it.namaTopik.contains(s.toString(), ignoreCase = true)
                }
                tampilkanTopikKeViewPager(hasilFilter)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun afterTextChanged(s: Editable?) {}
        })

        return view
    }

    // ✅ Fungsi bantu untuk isi ViewPager2
    private fun tampilkanTopikKeViewPager(dataTopik: List<TopikModel>) {
        val halamanTopik = dataTopik.chunked(3)
        val pagerAdapter = BankSoalPagerAdapter(halamanTopik)
        viewPager.adapter = pagerAdapter
        TabLayoutMediator(tabDots, viewPager) { _, _ -> }.attach()
    }
}
