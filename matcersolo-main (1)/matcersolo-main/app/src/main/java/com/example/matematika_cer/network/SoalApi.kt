package com.example.matematika_cer.network

import com.example.matematika_cer.model.Soal
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoalApi {
    @POST("soal/buat")
    fun buatSoal(@Body soal: Soal): Call<Soal>
}
