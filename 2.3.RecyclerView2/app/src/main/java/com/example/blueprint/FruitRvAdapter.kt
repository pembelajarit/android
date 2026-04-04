package com.example.blueprint

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class FruitRvAdapter(
    private val fruits: List<FruitItem>,
    private val onCreateHolder: (() -> Unit)? = null,
    private val onBind: (() -> Unit)? = null
) : RecyclerView.Adapter<FruitRvAdapter.FruitViewHolder>() {

    // ✅ ViewHolder: findViewById() hanya dipanggil SEKALI saat view dibuat
    inner class FruitViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFruit: ImageView = itemView.findViewById(R.id.ivFruit)
        val tvFruit: TextView = itemView.findViewById(R.id.tvFruit)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                Toast.makeText(
                    itemView.context,
                    "Kamu memilih: ${fruits[position].name}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitViewHolder {
        onCreateHolder?.invoke()
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_fruit, parent, false)
        return FruitViewHolder(view)
    }

    override fun onBindViewHolder(holder: FruitViewHolder, position: Int) {
        onBind?.invoke()
        val fruit = fruits[position]
        holder.ivFruit.setImageResource(fruit.imageResId)
        holder.tvFruit.text = fruit.name
    }

    override fun getItemCount() = fruits.size
}
