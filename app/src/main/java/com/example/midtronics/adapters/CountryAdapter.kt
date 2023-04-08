package com.example.midtronics.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.midtronics.CountryDetail
import com.example.midtronics.R

class CountryAdapter(private val names: Array<String>) : RecyclerView.Adapter<CountryAdapter.ViewHolder>() {
    val TAG = "CountryAdapterClass"
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.countries_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = names[position]
        holder.countryName.text = name
    }

    override fun getItemCount(): Int {
        return names.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val TAG = "CountryAdapterClass"
        val countryName: TextView = itemView.findViewById(R.id.country_name)
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,CountryDetail::class.java)
                intent.putExtra("country", countryName.text)
                itemView.context.startActivity(intent)
                Log.d(TAG, "Country Name: ${countryName.text}")
            }
        }
    }
}
