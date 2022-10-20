package com.example.rides.view.fragments

import com.example.rides.model.ResponseItem
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class VehicleDetailTest {

    lateinit var vehicleDetail: VehicleDetail

    @Before
    fun setUp(){
        var responseItem = ResponseItem(null,null,null, null,null,null,null,null, null,arrayListOf(),arrayListOf(),null,null,null,null)
        vehicleDetail = VehicleDetail(responseItem)

    }

    @Test
    fun verifyEstimatedEmission(){
        val value = vehicleDetail.calculateEmission(7000)
        assertEquals(value, 8000.0)
    }
}