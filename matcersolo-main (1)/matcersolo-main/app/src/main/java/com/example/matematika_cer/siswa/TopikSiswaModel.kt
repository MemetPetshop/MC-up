package com.example.matematika_cer.siswa

data class TopikSiswaModel(
    val nomor: Int,
    val namaTopik: String,
    val deskripsi: String,
    val kelas: String,
    val pembuat: String,
    val jumlahSoal: Int,
    val durasi: String
)
