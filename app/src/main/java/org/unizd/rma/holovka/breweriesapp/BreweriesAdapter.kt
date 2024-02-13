package org.unizd.rma.holovka.breweriesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BreweriesAdapter(
    private var breweries: List<Brewery>,
    private val onItemClickListener: (Brewery) -> Unit
) : RecyclerView.Adapter<BreweriesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_brewery, parent, false)
        return ViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val brewery = breweries[position]
        holder.bind(brewery)
    }

    override fun getItemCount() = breweries.size

    fun updateData(newBreweries: List<Brewery>) {
        breweries = newBreweries
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val onItemClickListener: (Brewery) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nameTextView: TextView = itemView.findViewById(R.id.textViewName)

        fun bind(brewery: Brewery) {
            nameTextView.text = brewery.name
            itemView.setOnClickListener { onItemClickListener(brewery) }
        }
    }
}
