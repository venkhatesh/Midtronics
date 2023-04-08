package com.example.midtronics.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.midtronics.repository.CountryDetailRepository

class CountryDeatilViewModelFactory(private val countryDetailRepository: CountryDetailRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CountryViewModel(countryDetailRepository) as T
    }
}