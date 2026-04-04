package com.example.blueprint

import android.os.Bundle
import android.os.SystemClock
import android.view.ViewTreeObserver
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ListViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

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
        val listView = findViewById<ListView>(R.id.listView)

        var inflateCount = 0
        var bindCount = 0
        var initTimeMs = 0L

        fun updateMetrics() {
            tvMetrics.text =
                "[ListView]  Init: ${initTimeMs}ms\n" +
                "Inflate view baru : $inflateCount\n" +
                "getView() + findViewById(): $bindCount  (dipanggil SETIAP scroll)"
        }

        val startTime = SystemClock.elapsedRealtime()

        listView.adapter = FruitAdapter(
            context = this,
            fruits = fruits,
            onInflate = { inflateCount++ },
            onBind = { bindCount++; updateMetrics() }
        )

        listView.viewTreeObserver.addOnGlobalLayoutListener(
            object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    listView.viewTreeObserver.removeOnGlobalLayoutListener(this)
                    initTimeMs = SystemClock.elapsedRealtime() - startTime
                    updateMetrics()
                }
            }
        )

        listView.setOnItemClickListener { _, _, position, _ ->
            Toast.makeText(this, "Kamu memilih: ${fruits[position].name}", Toast.LENGTH_SHORT).show()
        }
    }
}
