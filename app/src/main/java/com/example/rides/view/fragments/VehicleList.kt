package com.example.rides.view.fragments

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rides.R
import com.example.rides.adapter.MyAdapter
import com.example.rides.databinding.FragmentVehicleListBinding
import com.example.rides.model.ResponseItem
import com.example.rides.viewmodel.MainViewModel

class VehicleList : Fragment(), MyAdapter.OnItemClickListener {

    companion object {
        fun newInstance() = VehicleList()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var fragmentMainBinding: FragmentVehicleListBinding
    private lateinit var mAdapter: MyAdapter
    private var searchValue = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMainBinding = FragmentVehicleListBinding.inflate(inflater, container, false)
        return fragmentMainBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        activity?.title = getString(R.string.vehicle_list)
        viewModel.data.observe(viewLifecycleOwner) { it ->
            setAdapter(it.toMutableList())
            activity?.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            fragmentMainBinding.progressCircular.hide()
        }
        viewModel.exception.observe(viewLifecycleOwner) {
            showError(it)
        }
        handleClickOnButton()
        refreshItems()

    }


    //making network call for retrieve data
    private fun getData(name: Int) {
        viewModel.getData(name)
    }

    //handle click on button
    private fun handleClickOnButton() {
        fragmentMainBinding.searchButton.setOnClickListener {
            var inputText = fragmentMainBinding.searchEdittext.text.toString()
            if (inputText.isNotEmpty()) {
                searchValue = inputText.toInt()
                if (searchValue in 0..100) {
                    activity?.window?.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    );
                    fragmentMainBinding.progressCircular.show()
                    hideSoftKeyboard()
                    getData(searchValue)

                } else {
                    fragmentMainBinding.searchEdittext.error =
                        getString(R.string.valid_number_error)
                }
            } else {
                fragmentMainBinding.searchEdittext.error =
                    getString(R.string.empty_error)
            }
        }
    }

    //hiding soft keyboard on button click
    private fun hideSoftKeyboard() {
        var imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }

    //setting up data to adapter
    private fun setAdapter(mList: MutableList<ResponseItem>) {
        mList.sortBy { it.vin }
        mAdapter = MyAdapter(mList, this)
        fragmentMainBinding.recyclerview.layoutManager = LinearLayoutManager(activity)
        fragmentMainBinding.recyclerview.adapter = mAdapter
    }

    //refreshing list on swipe to down
    private fun refreshItems() {
        fragmentMainBinding.swipeRefresh.setOnRefreshListener {
            // if (searchValue > 0) {
            getData(searchValue)
            fragmentMainBinding.swipeRefresh.isRefreshing = false
            // }
        }
    }


    //opening vehicle detail fragment
    override fun onItemClick(responseItem: ResponseItem) {
        var vehicleDetail = VehicleDetail(responseItem)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.container, vehicleDetail, null)?.addToBackStack(null)?.commit()
    }

    //showing error message
    private fun showError(error: String) {
        if (viewLifecycleOwner.lifecycle.currentState == Lifecycle.State.RESUMED) {
            val dialog = AlertDialog.Builder(requireContext())
            dialog.setCancelable(false)
            dialog.setTitle(getString(R.string.error)).setMessage(error)
            dialog.setPositiveButton(
                getString(R.string.dimiss)
            ) { dialogInterface, p1 -> dialogInterface?.dismiss() }.show()
            activity?.window?.clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
            fragmentMainBinding.progressCircular.hide()
        }
    }
}