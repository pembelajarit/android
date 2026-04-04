package com.example.blueprint

import android.os.Bundle
import android.os.SystemClock
import android.view.ViewTreeObserver
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val icons = listOf(
            android.R.drawable.ic_menu_gallery,
            android.R.drawable.ic_menu_camera,
            android.R.drawable.ic_menu_compass,
            android.R.drawable.ic_menu_agenda
        )
        val fruits = List(5000) { index ->
            FruitItem(
                name = "Buah ${index + 1}",
                imageResId = icons[index % icons.size]
            )
        }

        val tvMetrics = findViewById<TextView>(R.id.tvMetrics)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        var createCount = 0
        var bindCount = 0
        var initTimeMs = 0L

        fun updateMetrics() {
            tvMetrics.text =
                "[RecyclerView]  Init: ${initTimeMs}ms\n" +
                "ViewHolder dibuat (onCreateViewHolder): $createCount  (hanya saat view baru)\n" +
                "onBindViewHolder(): $bindCount  (scroll terus bertambah)"
        }

        val startTime = SystemClock.elapsedRealtime()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        recyclerView.adapter = FruitRvAdapter(
            fruits = fruits,
            onCreateHolder = { createCount++ },
            onBind = { bindCount++; updateMetrics() }
        )

        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    initTimeMs = SystemClock.elapsedRealtime() - startTime
                    updateMetrics()
                }
            }
        )
    }
}
