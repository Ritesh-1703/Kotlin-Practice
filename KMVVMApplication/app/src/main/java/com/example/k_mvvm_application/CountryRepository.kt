package com.example.kmvvmapplication

import androidx.lifecycle.LiveData

class CountryRepository(private val dao: CountryDao) {

    val countries: LiveData<List<Country>> = dao.getAllCountries()

    suspend fun insert(country: Country) {
        dao.insert(country)
    }

    suspend fun update(country: Country) {
        dao.update(country)
    }

    suspend fun delete(country: Country) {
        dao.delete(country)
    }

    suspend fun deleteAll() {
        dao.deleteAll()
    }
}
