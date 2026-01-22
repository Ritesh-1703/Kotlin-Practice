package com.example.kotlin_recyclerview_fragment_application

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CountryAdapter(
    private val list: List<Country>
): RecyclerView.Adapter<CountryAdapter.CountryViewHolder> (){

    class CountryViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val nameText : TextView = itemView.findViewById(R.id.txtName1)
        val fruit: TextView = itemView.findViewById(R.id.txtFruit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_country,parent,false)

        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country =list[position]

        holder.nameText.text = country.name
        holder.fruit.text = country.fruits
    }

    override fun getItemCount(): Int {
        return list.size
    }
}