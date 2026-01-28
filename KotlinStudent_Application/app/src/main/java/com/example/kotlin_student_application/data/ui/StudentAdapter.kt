package com.example.kotlin_student_application.data.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_student_application.data.local.Student
import com.example.kotlin_student_application.databinding.ItemStudentBinding

class StudentAdapter(
    private val onItemClick: (Student) -> Unit,
    private val onDeleteClick: (Student) -> Unit,
    private val onEditClick: (Student) -> Unit
) : ListAdapter<Student, StudentAdapter.StudentViewHolder>(StudentDiffCallback()) {

    /**
     * ViewHolder - RecyclerView ki har item view ko hold karta hai
     * ViewBinding use karte hain findViewById se bachne ke liye
     */
    inner class StudentViewHolder(
        private val binding: ItemStudentBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(student: Student) {
            binding.apply {
                // Set data to views
                tvStudentName.text = student.name
                tvStudentClass.text = "Class: ${student.studentclass}"
                tvStudentMarks.text = "Marks: ${student.marks}"

                // Set grade based on marks
                tvStudentGrade.text = getGrade(student.marks)

                // Set click listeners
                root.setOnClickListener {
                    onItemClick(student)
                }

                btnDelete.setOnClickListener {
                    onDeleteClick(student)
                }

                btnEdit.setOnClickListener {
                    onEditClick(student)
                }
            }
        }

        private fun getGrade(marks: Int): String {
            return when {
                marks >= 90 -> "A+"
                marks >= 80 -> "A"
                marks >= 70 -> "B"
                marks >= 60 -> "C"
                marks >= 50 -> "D"
                else -> "F"
            }
        }
    }

    /**
     * DiffUtil Callback - Items compare karta hai
     * Efficient updates ke liye must hai
     */
    class StudentDiffCallback : DiffUtil.ItemCallback<Student>() {
        override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
            // Same object check (ID se compare karte hain)
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
            // Same content check (data compare karte hain)
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return StudentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = getItem(position)
        holder.bind(student)
    }

    /**
     * Filter function - Search implement karne ke liye
     */
    fun filterStudents(query: String, originalList: List<Student>) {
        val filteredList = if (query.isBlank()) {
            originalList
        } else {
            originalList.filter { student ->
                student.name.contains(query, ignoreCase = true) ||
                        student.studentclass.contains(query, ignoreCase = true)
            }
        }
        submitList(filteredList)
    }

    /**
     * Sort function
     */
    fun sortStudents(byMarks: Boolean = false, ascending: Boolean = true) {
        val currentList = currentList.toMutableList()

        val sortedList = if (byMarks) {
            if (ascending) {
                currentList.sortedBy { it.marks }
            } else {
                currentList.sortedByDescending { it.marks }
            }
        } else {
            if (ascending) {
                currentList.sortedBy { it.name }
            } else {
                currentList.sortedByDescending { it.name }
            }
        }

        submitList(sortedList)
    }
}

/**
 * DIFFUTIL KE FAYDE:
 * 1. Efficient updates - Sirf changed items update hote hain
 * 2. Smooth animations
 * 3. Better performance
 *
 * VIEWBINDING KE FAYDE:
 * 1. findViewById se bachata hai
 * 2. Type-safe
 * 3. Null-safe
 */