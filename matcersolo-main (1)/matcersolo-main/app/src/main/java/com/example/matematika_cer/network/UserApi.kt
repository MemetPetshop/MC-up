package com.example.matematika_cer.network

import com.example.matematika_cer.model.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("user/register")
    fun register(@Body user: User): Call<User>

    @POST("user/login")
    fun login(@Body user: User): Call<User>
}
