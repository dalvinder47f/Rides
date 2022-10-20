package com.example.rides.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.rides.R
import com.example.rides.databinding.FragmentVehicleDetailBinding
import com.example.rides.model.ResponseItem
import com.example.rides.viewmodel.MainViewModel

class VehicleDetail(private val responseItem: ResponseItem) : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentMainBinding: FragmentVehicleDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentVehicleDetailBinding.inflate(inflater, container, false)
        return fragmentMainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        activity?.title = getString(R.string.vehicle_detail)
        setData()
    }

    //setting up vehicle detail data
    private fun setData() {
        fragmentMainBinding.vin.text = getString(R.string.vin).plus(" : " + responseItem.vin)
        fragmentMainBinding.model.text =
            getString(R.string.make_and_model).plus(" : " + responseItem.makeAndModel)
        fragmentMainBinding.color.text = getString(R.string.color).plus(" : " + responseItem.color)
        fragmentMainBinding.carType.text =
            getString(R.string.car_type).plus(" : " + responseItem.carType)
    }
}