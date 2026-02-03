package com.example.ko_firebaseapplicationexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ko_firebaseapplicationexample.databinding.ActivityMainBinding
import com.example.ko_firebaseapplicationexample.databinding.DialogEditStudentBinding
import com.example.ko_firebaseapplicationexample.model.Student
import com.example.ko_firebaseapplicationexample.viewmodel.StudentViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: StudentAdapter

//    private var firestoreListener : ListenerRegistration? = null
    private val studentList = mutableListOf<Student>()

    private lateinit var viewModel: StudentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        viewModel = ViewModelProvider(this)[StudentViewModel::class.java]

        adapter = StudentAdapter(
            studentList,
            onEdit ={

                showDialog(it)
            },
            onDelete = {
                viewModel.deleteStudent(it)
            }

            )
        binding.recyclerStudent.layoutManager = LinearLayoutManager(this)
        binding.recyclerStudent.adapter = adapter

        viewModel.students.observe(this){
            studentList.clear()
            studentList.addAll(it)

            adapter.notifyDataSetChanged()
        }

      viewModel.startListening()

        binding.btnAddStudent.setOnClickListener {
            val name = binding.etStudentName.text.toString()
            val ageText = binding.etStudentAge.text.toString()
            val age = ageText.toIntOrNull() ?: 0
            val course = binding.etStudentCourse.text.toString()
            viewModel.addStudent(
                Student(
                    name = name,
                    age = age,
                    course = course
                )
            )

            binding.etStudentAge.text.clear()
            binding.etStudentCourse.text.clear()
            binding.etStudentName.text.clear()
        }

    }


    private fun showDialog(student: Student) {

        val dialogBinding = DialogEditStudentBinding.inflate(layoutInflater)

        dialogBinding.etEditName.setText(student.name)
        dialogBinding.etEditAge.setText(student.age.toString())
        dialogBinding.etEditCourse.setText(student.course)

        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Edit Student")
            .setView(dialogBinding.root)
            .setCancelable(false)
            .create()

        dialogBinding.update.setOnClickListener {

            val updatedStudent = student.copy(
                name = dialogBinding.etEditName.text.toString(),
                age = dialogBinding.etEditAge.text.toString().toIntOrNull() ?: 0,
                course = dialogBinding.etEditCourse.text.toString()
            )

            viewModel.updateStudent(updatedStudent)
            dialog.dismiss()
        }

        dialog.show()
    }

}

//binding.btnAddStudent.setOnClickListener {
//    val name = binding.etStudentName.text.toString()
//    val ageText = binding.etStudentAge.text.toString()
//    val age = ageText.toIntOrNull() ?: 0
//    val course = binding.etStudentCourse.text.toString()
//}

//if(name.isNotEmpty() && age > 0 && course.isNotEmpty()) {
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

//        val docRef = FirebaseUtils.database
//            .collection("students")
//            .document()
//
//        val student = Student(
//            id = docRef.id,
//            name = name,
//            age = age,
//            course = course
//        )
//
//        docRef.set(student)
//
//        Toast.makeText(this, "Data Added Successfully!!", Toast.LENGTH_SHORT).show()
//
//        binding.etStudentAge.text.clear()
//        binding.etStudentCourse.text.clear()
//        binding.etStudentName.text.clear()
//
//        firestoreListener = FirebaseUtils.database
//            .collection("students")
//            .addSnapshotListener { snapshot, error ->
//                if (error != null || snapshot == null) {
//                    Toast.makeText(this, "Error: ${error?.message}", Toast.LENGTH_SHORT).show()
//                    return@addSnapshotListener
//                }
//
//                studentList.clear()
//                for (doc in snapshot.documents) {
//                    val student = doc.toObject(Student::class.java)
//                    if (student != null) {
//                        studentList.add(student)
//                    }
//                }
//                adapter.notifyDataSetChanged()
//            }

//        override fun onDestroy() {
//            super.onDestroy()
//            firestoreListener?.remove() // memory leak avoid
//        }

//

