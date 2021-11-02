package com.example.demoapp.retrofit

import com.example.demoapp.pojos.RegisterResponse
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query


interface APIService {
    @POST("register")
    fun createProfile(@Query("email") email: String, @Query("name") name: String, @Query("isd") isd: String, @Query("phone") phone: String, @Query("password") password: String, @Query("utype") utype: String): Call<RegisterResponse>
}