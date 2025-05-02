package com.example.matematika_cer.model

data class Soal(
    val id: Long? = null,
    val pertanyaan: String,
    val opsiA: String,
    val opsiB: String,
    val opsiC: String,
    val opsiD: String,
    val jawaban: String,
    val topik: Topik // Reuse class Topik (yang sudah ada)
)
