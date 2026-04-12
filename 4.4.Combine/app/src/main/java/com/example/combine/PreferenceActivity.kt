package com.example.combine

import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PreferenceActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_preference)

        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        sharedPreferences.edit().putString("last_activity","PreferenceActivity").apply()

        nameEditText = findViewById<EditText>(R.id.edit_name)
        ageEditText = findViewById<EditText>(R.id.edit_age)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()

//        menerima data dari Shared Preferences
val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val savedName = sharedPreferences.getString("user_name","")
        val savedAge = sharedPreferences.getInt("user_age",0)

//        mengisi kolom EditText dengan data yang tersimpan
        nameEditText.setText(savedName)
        ageEditText.setText(if (savedAge > 0) savedAge.toString() else "0")
    }

    override fun onPause() {
        super.onPause()

        // menyimpan data di Shared Preferences
        val sharedPreferences = getSharedPreferences("UserPreferences", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        // menerima user input dan menyimpannya
        editor.putString("user_name",nameEditText.text.toString())

        val ageInput = ageEditText.text.toString()
        val userAge = if (ageInput.isEmpty()) 0 else ageInput.toInt()
        editor.putInt("user_age", userAge)

        editor.apply()
    }
}