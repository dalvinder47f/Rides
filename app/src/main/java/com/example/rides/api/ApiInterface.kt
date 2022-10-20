package com.example.rides.api


import com.example.rides.model.ResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("vehicle/random_vehicle")
    suspend fun getData(@Query("size") size : Int) : Response<List<ResponseItem>>
}