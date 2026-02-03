package com.example.ko_firebaseapplicationexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ko_firebaseapplicationexample.databinding.ItemStudentBinding
import com.example.ko_firebaseapplicationexample.model.Student



class StudentAdapter(
    private val list: List<Student>,
    private val onEdit: (Student) -> Unit,
    private val onDelete: (Student) -> Unit
): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {


    inner class ViewHolder( val binding: ItemStudentBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        val binding2= ItemStudentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )


        return ViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val stu = list[position]


        holder.binding.tvName.text = list[position].name
        holder.binding.tvAge.text = list[position].age.toString()
        holder.binding.tvCourse.text = list[position].course

        holder.binding.editButton.setOnClickListener {
            val updated = stu.copy(
                name =  stu.name ,
                age = stu.age,
                course = stu.course
            )
            onEdit(updated)

        }

        holder.binding.deleteButton.setOnClickListener {
            onDelete(stu)
        }

    }

    override fun getItemCount(): Int {
        return list.size

    }

}