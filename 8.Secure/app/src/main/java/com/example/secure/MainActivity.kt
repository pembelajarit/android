package com.example.secure

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
//    val apiKey = "sk-1234567890abcdef"   // BAHAYA!
//    val baseUrl = "https://api.rahasia.com" // BOCOR!
    val apiKey = BuildConfig.API_KEY   // Aman!
    val baseUrl = BuildConfig.BASE_URL // Aman!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.d("val","apikey $apiKey")
        Log.d("val","baseUrl $baseUrl")
    }
}