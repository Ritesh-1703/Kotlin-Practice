package com.example.kmvvmapplication2.data.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.kmvvmapplication2.data.Repository.CricketerRepository
import com.example.kmvvmapplication2.data.local.Cricketer
import com.example.kmvvmapplication2.data.local.CricketerDatabase
import kotlinx.coroutines.launch

class CricketerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CricketerRepository
    val allCricketers: LiveData<List<Cricketer>>

    init {
        val dao = CricketerDatabase.getDatabase(application).cricketerDao()
        repository = CricketerRepository(dao)
        allCricketers = repository.allCricketers
    }

    fun insert(cricketer: Cricketer) = viewModelScope.launch {
        repository.insert(cricketer)
    }

    fun delete(cricketer: Cricketer) = viewModelScope.launch {
        repository.delete(cricketer)
    }

    fun update(cricketer: Cricketer) = viewModelScope.launch {
        repository.update(cricketer)
    }
}
