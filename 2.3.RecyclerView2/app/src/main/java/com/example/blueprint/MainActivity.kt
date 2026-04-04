package com.example.blueprint

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnGoToListView).setOnClickListener {
            startActivity(Intent(this, ListViewActivity::class.java))
        }

        findViewById<Button>(R.id.btnGoToRecyclerView).setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }
    }
}