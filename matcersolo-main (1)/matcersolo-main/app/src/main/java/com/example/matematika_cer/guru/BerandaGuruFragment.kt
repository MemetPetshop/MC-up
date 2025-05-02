package com.example.matematika_cer.guru


import androidx.navigation.fragment.findNavController

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import com.example.matematika_cer.loginregis.LoginActivity
import com.example.matematika_cer.R

class BerandaGuruFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_beranda_guru, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val namaTextView = view.findViewById<TextView>(R.id.namaGuruBeranda)
        val namaGuru = arguments?.getString("nama") ?: ""
        namaTextView.text = "Halo Ibu $namaGuru !!"

        val daftarTopik = view.findViewById<CardView>(R.id.daftar_topik)
        val bankSoal = view.findViewById<CardView>(R.id.bank_soal)
        val buatTopik = view.findViewById<CardView>(R.id.buat_topikdansoal)
        val editTopik = view.findViewById<CardView>(R.id.edit_topikdansoal)
        val logoutBtn = view.findViewById<View>(R.id.logoutbtn)

        daftarTopik.setOnClickListener {
            findNavController().navigate(R.id.action_beranda_to_daftarTopik)
        }
        bankSoal.setOnClickListener {
            findNavController().navigate(R.id.action_beranda_to_bankSoal)
        }
        buatTopik.setOnClickListener {
            findNavController().navigate(R.id.action_beranda_to_tambahTopik)
        }
        editTopik.setOnClickListener {
            findNavController().navigate(R.id.action_beranda_to_editTopik)
        }

        logoutBtn.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Konfirmasi Logout")
            builder.setMessage("Apakah Anda yakin ingin logout dan kembali ke halaman login?")

            builder.setPositiveButton("Ya") { dialog, _ ->
                Toast.makeText(requireContext(), "Logout berhasil", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }, 1000)

                dialog.dismiss()
            }

            builder.setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }

            builder.show()
        }
    }
}
