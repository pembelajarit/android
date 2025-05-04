package com.example.sqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var addName: Button
    private lateinit var printName: Button
    private lateinit var enterName: EditText
    private lateinit var enterAge: EditText
    private lateinit var name: TextView
    private lateinit var age: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize UI components
        addName = findViewById(R.id.addName)
        printName = findViewById(R.id.printName)
        enterName = findViewById(R.id.enterName)
        enterAge = findViewById(R.id.enterAge)
        name = findViewById(R.id.name)
        age = findViewById(R.id.age)

        // Create database helper instance
        val db = DBHelper(this, null)

        // Add data to database on button click
        addName.setOnClickListener {
            val inputName = enterName.text.toString().trim()
            val inputAge = enterAge.text.toString().trim()

            if (inputName.isEmpty() || inputAge.isEmpty()) {

                // Show message if fields are empty
                Toast.makeText(this, "Please enter both name and age", Toast.LENGTH_SHORT).show()
            } else {

                // Add name and age to database
                db.addName(inputName, inputAge)
                Toast.makeText(this, "$inputName added to database", Toast.LENGTH_LONG).show()

                // Clear input fields
                enterName.text.clear()
                enterAge.text.clear()
            }
        }

        // Retrieve and display data from database on button click
        printName.setOnClickListener {
            name.text = ""
            age.text = ""

            val cursor = db.getName()

            cursor.use {
                if (cursor.moveToFirst()) {
                    do {
                        val personName =
                            cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.NAME_COL))
                        val personAge =
                            cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.AGE_COL))

                        // Append data to text views
                        name.append("$personName\n")
                        age.append("$personAge\n")
                    } while (cursor.moveToNext())
                }
            }
        }
    }
}