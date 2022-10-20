package com.example.rides

import com.example.rides.api.ApiInterface
import com.example.rides.api.RetrofitHelper
import com.example.rides.model.ResponseItem
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class NetworkAPITest {

    lateinit var apiInterface: ApiInterface

    @Before
    fun setUp() {
        apiInterface = RetrofitHelper.getInstance().create(ApiInterface::class.java)
    }

    @Test
    fun getData() = runTest {
        var data = fetchData(10)
        assertEquals(data.size, 10)
    }

    private suspend fun fetchData(size: Int): List<ResponseItem> {
        return apiInterface.getData(size).body()!!
    }
}