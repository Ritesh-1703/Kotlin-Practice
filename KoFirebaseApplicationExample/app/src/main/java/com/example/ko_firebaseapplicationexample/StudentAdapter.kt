package com.example.ko_firebaseapplicationexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ko_firebaseapplicationexample.databinding.ItemStudentBinding
import com.example.ko_firebaseapplicationexample.model.Student


class StudentAdapter(
    private val list: List<Student>
): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {


    inner class ViewHolder( val binding: ItemStudentBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStudentBinding.inflate(
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


    }

    override fun getItemCount(): Int {
        return list.size

    }

}