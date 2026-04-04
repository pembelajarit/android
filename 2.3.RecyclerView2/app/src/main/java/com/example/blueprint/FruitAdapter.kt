package com.example.blueprint

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class FruitAdapter(
    context: Context,
    private val fruits: List<FruitItem>,
    private val onInflate: (() -> Unit)? = null,
    private val onBind: (() -> Unit)? = null
) : ArrayAdapter<FruitItem>(context, R.layout.item_fruit, fruits) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = if (convertView != null) {
            convertView
        } else {
            onInflate?.invoke()
            LayoutInflater.from(context).inflate(R.layout.item_fruit, parent, false)
        }
        onBind?.invoke()

        val ivFruit = view.findViewById<ImageView>(R.id.ivFruit)
        val tvFruit = view.findViewById<TextView>(R.id.tvFruit)
        val fruit = fruits[position]
        ivFruit.setImageResource(fruit.imageResId)
        tvFruit.text = fruit.name

        return view
    }
}
