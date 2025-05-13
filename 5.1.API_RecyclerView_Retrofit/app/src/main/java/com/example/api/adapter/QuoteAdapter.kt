package com.example.api.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.api.R
import com.example.api.model.Quote

class QuoteAdapter(private val quotes: List<Quote>) : RecyclerView.Adapter<QuoteAdapter.QuoteViewHolder>() {

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quoteText: TextView = itemView.findViewById(R.id.text_quote)
        val authorText: TextView = itemView.findViewById(R.id.text_author)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_quote, parent, false)
        return QuoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item = quotes[position]
        holder.quoteText.text = item.quote
        holder.authorText.text = "- ${item.author}"
    }

    override fun getItemCount(): Int = quotes.size
}
