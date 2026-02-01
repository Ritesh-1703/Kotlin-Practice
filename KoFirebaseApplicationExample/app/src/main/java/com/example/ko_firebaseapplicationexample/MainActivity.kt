package com.example.ko_firebaseapplicationexample

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ko_firebaseapplicationexample.databinding.ActivityMainBinding
import com.example.ko_firebaseapplicationexample.model.Student
import com.example.ko_firebaseapplicationexample.utils.FirebaseUtils
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.firestoreSettings

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: StudentAdapter

    private var firestoreListener : ListenerRegistration? = null
    private val studentList = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        adapter = StudentAdapter(studentList)
        binding.recyclerStudent.layoutManager = LinearLayoutManager(this)
        binding.recyclerStudent.adapter = adapter

        binding.btnAddStudent.setOnClickListener {

            val name = binding.etStudentName.text.toString()
            val ageText = binding.etStudentAge.text.toString()
            val age = ageText.toIntOrNull() ?: 0
            val course = binding.etStudentCourse.text.toString()

            if (name.isNotEmpty() && age > 0 && course.isNotEmpty()) {

                val docRef = FirebaseUtils.database
                    .collection("students")
                    .document()

                val student = Student(
                    id = docRef.id,
                    name = name,
                    age = age,
                    course = course
                )

                docRef.set(student)

                Toast.makeText(this, "Data Added Successfully!!", Toast.LENGTH_SHORT).show()

                binding.etStudentAge.text.clear()
                binding.etStudentCourse.text.clear()
                binding.etStudentName.text.clear()

            }
        }

        firestoreListener = FirebaseUtils.database
            .collection("students")
            .addSnapshotListener { snapshot, error ->
                if (error != null || snapshot == null) {
                    Toast.makeText(this, "Error: ${error?.message}", Toast.LENGTH_SHORT).show()
                    return@addSnapshotListener
                }

                studentList.clear()
                for (doc in snapshot.documents) {
                    val student = doc.toObject(Student::class.java)
                    if (student != null) {
                        studentList.add(student)
                    }
                }
                adapter.notifyDataSetChanged()
            }
    }


    override fun onDestroy() {
        super.onDestroy()
        firestoreListener?.remove() // memory leak avoid
    }








//            if(name.isNotEmpty() && age > 0 && course.isNotEmpty()) {
//
//                val id = FirebaseUtils.database
//                    .child("students")
//                    .push()
//                    .key!!
//
//
//                val student = Student(
//                    id = id.id,
//                    name = name,
//                    age = age,
//                    course = course
//                )
//
//
//                FirebaseUtils.database
//                    .child("students")
//                    .child(id)
//                    .setValue(student)
//                    .addOnCompleteListener { task ->
//                        if (task.isSuccessful) {
//                            Toast.makeText(this, "Student Added Successfully", Toast.LENGTH_SHORT).show()
//
//                            binding.etStudentAge.text.clear()
//                            binding.etStudentCourse.text.clear()
//                            binding.etStudentName.text.clear()
//                        } else {
//                            Toast.makeText(
//                                this,
//                                "Failed: ${task.exception?.message}",
//                                Toast.LENGTH_LONG
//                            ).show()
//                        }
//                    }
//            }else {
//                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
//            }
//
//        }
//
//        FirebaseUtils.database
//            .child("students")
//            .addValueEventListener(object : ValueEventListener{
//                override fun onDataChange(snapshot: DataSnapshot) {
//                    studentList.clear()
//                    for (data in snapshot.children) {
//                        val student = data.getValue(Student::class.java)
//                       student?.let {
//                           studentList.add(it)
//                       }
//                    }
//                    adapter.notifyDataSetChanged()
//                }
//
//                override fun onCancelled(error: DatabaseError) {
//                }
//
//            })
//




}