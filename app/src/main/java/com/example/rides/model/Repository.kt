package com.example.rides.model

import com.example.rides.api.ApiInterface
import com.example.rides.api.RetrofitHelper
import com.example.rides.model.Result.*
import java.lang.*

/**
 * Class that requests data from the remote and return to view-model
 */

class Repository() {

    //Creating API client and making network call
   suspend fun getData(size : Int): Result<List<ResponseItem>> {
        lateinit var apiClient : ApiInterface
        return try {
            apiClient = RetrofitHelper.getInstance().create(ApiInterface::class.java)
            val result = apiClient.getData(size)
            if (result.isSuccessful)
            return  Success(result.body())
            else Result.Error(Exception(result.message()))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

}