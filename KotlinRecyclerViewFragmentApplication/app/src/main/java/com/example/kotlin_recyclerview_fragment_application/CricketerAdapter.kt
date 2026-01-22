package com.example.kotlin_recyclerview_fragment_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ListMenuItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class CricketerAdapter(private val list: List<Cricketer>): RecyclerView.Adapter<CricketerAdapter.CricketerViewHolder>() {
    class CricketerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameText: TextView = itemView.findViewById(R.id.txtName)
        val runs: TextView = itemView.findViewById(R.id.txtRuns)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CricketerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_cricketer,parent,false)

        return CricketerViewHolder(view)
    }

    override fun onBindViewHolder(holder: CricketerViewHolder, position: Int) {
        val cricketer =list[position]

        holder.nameText.text = cricketer.name
        holder.runs.text= "Runs: ${cricketer.runs}"
    }

    override fun getItemCount(): Int {
        return list.size
    }
}