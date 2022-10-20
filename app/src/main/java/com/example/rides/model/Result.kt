package com.example.rides.model
import java.lang.Exception

/**
 * A generic class that holds a value with its loading status.
 */
sealed class Result<Any> {
    data class Success(val data: List<ResponseItem>?) : Result<List<ResponseItem>>()
     data class Error(val error : Exception) : Result<List<ResponseItem>>()
}