package com.example.rides.api


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * A class that create a client for network call
 */
object RetrofitHelper {

    private val client = OkHttpClient
        .Builder()
        .build()

    private const val baseUrl = "https://random-data-api.com/api/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }
}