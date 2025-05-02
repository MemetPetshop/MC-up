package com.example.matematika_cer.guru

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.matematika_cer.R

class TopikItemFragment : Fragment() {

    companion object {
        fun newInstance(topik: TopikModel): TopikItemFragment {
            val fragment = TopikItemFragment()
            val args = Bundle()
            args.putParcelable("topik", topik)
            fragment.arguments = args
            return fragment
        }
    }

    private var topik: TopikModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        topik = arguments?.getParcelable("topik")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.item_topik_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<TextView>(R.id.nama_topik).text = topik?.namaTopik
        view.findViewById<TextView>(R.id.durasi).text = "Durasi: ${topik?.durasi}"
        view.findViewById<TextView>(R.id.total_soal).text = "Jumlah soal: ${topik?.jumlahSoal}"
        view.findViewById<TextView>(R.id.peserta).text =
            "👥 ${topik?.jumlahMenjawab}/${topik?.totalPeserta} Siswa"
        view.findViewById<TextView>(R.id.tanggal).text = "Tanggal: ${topik?.tanggal}"
    }
}
