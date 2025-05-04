package com.example.a42file

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {
    // Declare the variables
    private lateinit var read: Button
    private lateinit var write: Button
    private lateinit var userInput: EditText
    private lateinit var fileContent: TextView

    private val filename = "myfile.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Initialize the UI components
        read = findViewById(R.id.read_button)
        write = findViewById(R.id.write_button)
        userInput = findViewById(R.id.userInput)
        fileContent = findViewById(R.id.content)

        // Set click listeners
        read.setOnClickListener(this)
        write.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val b = view as Button
        val bText = b.text.toString().lowercase()

        when (bText) {
            "write" -> writeData()
            "read" -> readData()
        }
    }

    private fun writeData() {
        try {
            openFileOutput(filename, Context.MODE_PRIVATE).use { fos ->
                val data = userInput.text.toString()
                fos.write(data.toByteArray())
            }
            userInput.setText("")  // Clear the input field
            printMessage("Writing to file $filename completed...")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun readData() {
        try {
            val temp = StringBuilder()
            openFileInput(filename).use { fin ->
                var a: Int
                while (fin.read().also { a = it } != -1) {
                    temp.append(a.toChar())
                }
            }
            fileContent.text = temp.toString()
            printMessage("Reading from file $filename completed...")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    // A simple method to print messages to the fileContent TextView
    private fun printMessage(message: String) {
        fileContent.append("\n$message")
    }
}