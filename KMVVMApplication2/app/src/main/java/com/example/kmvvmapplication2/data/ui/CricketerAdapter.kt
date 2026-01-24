package com.example.kmvvmapplication2.data.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kmvvmapplication2.R
import com.example.kmvvmapplication2.data.local.Cricketer

class CricketerAdapter(
    private val onDelete: (Cricketer) -> Unit,
    private  val onUpdate: (Cricketer) -> Unit
) : RecyclerView.Adapter<CricketerAdapter.ViewHolder>() {

    private var list = emptyList<Cricketer>()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvName)
        val runs: TextView = view.findViewById(R.id.tvRuns)
        val delete: ImageButton = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cricketer, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cricketer = list[position]
        holder.name.text = cricketer.name
        holder.runs.text = "Runs: ${cricketer.runs}"

        holder.delete.setOnClickListener {
            onDelete(cricketer)
        }
        holder.itemView.setOnClickListener {
            onUpdate(cricketer)
        }
    }

    override fun getItemCount(): Int = list.size

    fun setData(newList: List<Cricketer>) {
        list = newList
        notifyDataSetChanged()
    }
}