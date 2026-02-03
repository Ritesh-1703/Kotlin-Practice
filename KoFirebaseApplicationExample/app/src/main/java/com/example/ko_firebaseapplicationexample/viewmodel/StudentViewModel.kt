package com.example.ko_firebaseapplicationexample.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ko_firebaseapplicationexample.model.Student
import com.example.ko_firebaseapplicationexample.repository.StudentRepository
import com.google.firebase.firestore.ListenerRegistration

class StudentViewModel: ViewModel() {

    private val repo = StudentRepository()

    val students= MutableLiveData<List<Student>>()

    private var listener: ListenerRegistration? =null

    fun startListening(){
        listener = repo.listenStudent {
            students.postValue( it)
        }

    }

    fun addStudent(student: Student){
        repo.addStudent(student)
    }

    fun updateStudent(student: Student){
        repo.updateStudent(student)
    }

    fun deleteStudent(student: Student){
        repo.deleteStudent(student)
    }

    override fun onCleared() {
        super.onCleared()
        listener?.remove()
    }



}