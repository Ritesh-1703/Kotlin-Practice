package com.example.ko_firebaseapplicationexample.repository

import com.example.ko_firebaseapplicationexample.model.Student
import com.example.ko_firebaseapplicationexample.utils.FirebaseUtils
import com.google.firebase.firestore.ListenerRegistration

class StudentRepository {

    val studentRef  = FirebaseUtils.database
        .collection("students")


    fun listenStudent(onResult: (List<Student>)->Unit): ListenerRegistration{
        return studentRef.addSnapshotListener {
            snapshot, error ->
            if (error != null || snapshot == null) {
                return@addSnapshotListener
            }

            val list = snapshot?.documents?.mapNotNull {doc ->
              val student = doc.toObject(Student::class.java)
                student?.copy(id = doc.id)
            }?: emptyList()
            onResult(list)
        }
    }

    fun addStudent(student: Student){
        val doc = studentRef.document()
        val newStudent = student.copy(id = doc.id)
        doc.set(newStudent)
    }

    fun updateStudent(student: Student){
//        studentRef.document(student.id).set(student)
        if (student.id.isBlank()) return   // safety
        studentRef.document(student.id).update(
            mapOf(
                "name" to student.name,
                "age" to student.age,
                "course" to student.course
            )
        )
    }

    fun deleteStudent(student: Student) {
        if (student.id.isBlank()) return
        studentRef.document(student.id).delete()
    }
}