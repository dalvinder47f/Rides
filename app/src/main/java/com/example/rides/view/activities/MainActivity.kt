package com.example.rides.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rides.R
import com.example.rides.view.fragments.VehicleList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VehicleList.newInstance())
                .commitNow()
        }
    }
}