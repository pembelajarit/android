package com.example.api

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.api.adapter.QuoteAdapter
import com.example.api.network.RetrofitInstance
import com.example.api.response.QuoteResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var quoteAdapter: QuoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchQuotes()
    }

    private fun fetchQuotes() {
        RetrofitInstance.api.getQuotes().enqueue(object : Callback<QuoteResponse> {
            override fun onResponse(call: Call<QuoteResponse>, response: Response<QuoteResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val quotes = response.body()!!.quotes
                    quoteAdapter = QuoteAdapter(quotes)
                    recyclerView.adapter = quoteAdapter
                } else {
                    Toast.makeText(this@MainActivity, "Failed to load quotes", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<QuoteResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error: ${t.message}", Toast.LENGTH_LONG).show()
                Log.e("API_CALL", "onFailure: ", t)
            }
        })
    }
}