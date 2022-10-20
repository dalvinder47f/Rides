package com.example.rides.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rides.model.ResponseItem
import com.example.rides.model.Result
import com.example.rides.model.Repository
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _result = MutableLiveData<List<ResponseItem>>()
    private val _exception = MutableLiveData<String>()
    val data: LiveData<List<ResponseItem>> = _result
    val exception: LiveData<String> = _exception


    //making a network request
    fun getData(size : Int){
        viewModelScope.launch {
            when(val data : Result<List<ResponseItem>> =  Repository()?.getData(size)!!){
                is Result.Success -> _result.postValue(data.data!!)
                is Result.Error -> _exception.postValue(data.error.localizedMessage!!)
            }
        }
    }
}