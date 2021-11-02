package com.example.demoapp.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var BASE_URL = "http://anyrentals.ae/rentalapi/profile/"  //http://anyrentals.ae/rentalapi/profile/register
    var retrofit: Retrofit? = null
    fun getInstance() : Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}
