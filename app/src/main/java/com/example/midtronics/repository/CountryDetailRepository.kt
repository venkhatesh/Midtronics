package com.example.midtronics.repository

import android.util.Log
import com.example.midtronics.RetrofitService
import com.example.midtronics.model.Country

class CountryDetailRepository() {
    val TAG = "CountryDetailRepository"

    suspend fun getCountryDetail(countryName : String) : List<Country>? {
        var responseResult = RetrofitService.invoke().getCoutryDetail(countryName)
        Log.d(TAG, "getCountryDetail: ${responseResult.body()?.get(0)?.name}")
        return responseResult.body()
    }
}