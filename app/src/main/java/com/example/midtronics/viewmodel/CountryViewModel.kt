package com.example.midtronics.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.midtronics.model.Country
import com.example.midtronics.repository.CountryDetailRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.DisposableHandle
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CountryViewModel(private val countryDetailRepository: CountryDetailRepository): ViewModel() {
    val TAG = "CountryViewModel"
    var liveResult = MutableLiveData<List<Country>>()
    val loading = MutableLiveData<Boolean>()

    fun getCountryDetail(countryName: String){
            viewModelScope.launch {
                withContext(Dispatchers.IO ){
                    var result = countryDetailRepository.getCountryDetail(countryName)
                    result?.let {
                        loading.postValue(false)
                        liveResult.postValue(it)
                    }?: kotlin.run {
                        Log.d(TAG, "getCountryDetail: INSIDE ON FAILURE")
                        loading.postValue(false)
                    }
                }
            }
    }
}