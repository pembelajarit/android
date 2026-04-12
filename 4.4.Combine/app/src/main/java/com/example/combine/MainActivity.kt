package com.example.combine

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File

class MainActivity : AppCompatActivity() {
    private lateinit var btn_pref: Button
    private lateinit var btn_file: Button
    private lateinit var btn_db: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val lastActivity = sharedPreferences.getString("last_activity", null)

//        pindah ke activity terakhir jika ada
        val targetActivity = when (lastActivity) {
            "PreferenceActivity" -> PreferenceActivity::class.java
            "FileActivity" -> FileActivity::class.java
            "DbActivity" -> DbActivity::class.java
            else -> null
        }

        targetActivity?.let {
            startActivity(Intent(this,it))
//            finish()
        }

        btn_pref = findViewById<Button>(R.id.btn_preference)
        btn_file = findViewById<Button>(R.id.btn_file)
        btn_db = findViewById<Button>(R.id.btn_sqlite)

//        Map id tombol ke activity tujuan
        val buttonActions = mapOf(
            R.id.btn_preference to PreferenceActivity::class.java,
            R.id.btn_file to FileActivity::class.java,
            R.id.btn_sqlite to DbActivity::class.java
        )

//        Listener tunggal
        val clickListener = View.OnClickListener {view ->
            buttonActions[view.id]?.let {targetActivity ->
                val intent = Intent(this, targetActivity)
                startActivity(intent)
            }
        }

//        Loop set listener
        listOf(btn_pref, btn_file, btn_db).forEach {
            it.setOnClickListener (clickListener)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}