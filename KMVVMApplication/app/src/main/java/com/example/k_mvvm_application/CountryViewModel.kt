package com.example.kmvvmapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class CountryViewModel(application: Application)
    : AndroidViewModel(application) {

    private val dao =
        AppDatabase.getDatabase(application).countryDao()

    private val repository = CountryRepository(dao)

    val countries = repository.countries

    fun insert(country: Country) = viewModelScope.launch {
        repository.insert(country)
    }

    fun update(country: Country) = viewModelScope.launch {
        repository.update(country)
    }

    fun delete(country: Country) = viewModelScope.launch {
        repository.delete(country)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }
}
