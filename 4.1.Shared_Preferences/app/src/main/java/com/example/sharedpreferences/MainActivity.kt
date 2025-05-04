package com.example.sharedpreferences

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        nameEditText = findViewById(R.id.edit1)
        ageEditText = findViewById(R.id.edit2)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()

        // Retrieving stored data from SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val savedName = sharedPreferences.getString("user_name", "")
        val savedAge = sharedPreferences.getInt("user_age", 0)

        // Populating EditText fields with stored data
        nameEditText.setText(savedName)
        ageEditText.setText(if (savedAge > 0) savedAge.toString() else "")
    }

    override fun onPause() {
        super.onPause()

        // Storing data in SharedPreferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // Retrieving user input and saving it
        editor.putString("user_name", nameEditText.text.toString())

        val ageInput = ageEditText.text.toString()
        val userAge = if (ageInput.isEmpty()) 0 else ageInput.toInt()
        editor.putInt("user_age", userAge)

        editor.apply()
    }
}