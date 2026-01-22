package com.example.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class CricketerAddapter (
    private  val list: List<Cricketer>
): RecyclerView.Adapter<CricketerAddapter.CricketerViewHolder>(){
    class CricketerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameText: TextView = itemView.findViewById(R.id.txtName)
        val runText: TextView = itemView.findViewById(R.id.txtRuns)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CricketerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_cricketer,parent,false)

        return CricketerViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CricketerViewHolder, position: Int) {
        val cricketer = list[position]

        holder.nameText.text = cricketer.name
        holder.runText.text= "Runs: ${cricketer.runs}"

    }

    override fun getItemCount(): Int {
        return list.size
    }
    }

