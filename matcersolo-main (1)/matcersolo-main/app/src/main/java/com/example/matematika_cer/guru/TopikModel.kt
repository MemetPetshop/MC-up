package com.example.matematika_cer.guru

import com.example.matematika_cer.siswa.SoalModel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TopikModel(
    val namaTopik: String,
    val durasi: String,
    val jumlahSoal: Int,
    val tanggal: String,
    val isAktif: Boolean? = null,
    val jumlahMenjawab: Int? = null,
    val totalPeserta: Int? = null,
    val soalList: List<SoalModel> = emptyList()
) : Parcelable

