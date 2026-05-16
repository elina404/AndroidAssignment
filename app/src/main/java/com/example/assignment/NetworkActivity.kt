package com.example.assignment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {

    private lateinit var resultTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        resultTextView = findViewById(R.id.resultTextView)
        statusTextView = findViewById(R.id.statusTextView)
        progressBar = findViewById(R.id.progressBar)
        val btnFetchData = findViewById<Button>(R.id.btnFetchData)

        btnFetchData.setOnClickListener {
            fetchDataFromApi()
        }
    }

    private fun fetchDataFromApi() {
        progressBar.visibility = View.VISIBLE
        statusTextView.text = "Status: Fetching..."
        resultTextView.text = ""

        // Chapter 5 Task 1, 2, 3 & 5: Fetch, Parse JSON, and Error Handling
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                // Using a public API for demonstration
                val url = URL("https://jsonplaceholder.typicode.com/posts?_limit=5")
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 5000
                connection.readTimeout = 5000

                val responseCode = connection.responseCode
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val responseText = connection.inputStream.bufferedReader().use { it.readText() }
                    
                    // Parse JSON Array
                    val jsonArray = JSONArray(responseText)
                    val formattedResult = StringBuilder()
                    
                    for (i in 0 until jsonArray.length()) {
                        val obj = jsonArray.getJSONObject(i)
                        val title = obj.getString("title")
                        val body = obj.getString("body")
                        formattedResult.append("ID: ${obj.getInt("id")}\n")
                        formattedResult.append("TITLE: $title\n")
                        formattedResult.append("BODY: $body\n\n")
                    }

                    withContext(Dispatchers.Main) {
                        progressBar.visibility = View.GONE
                        statusTextView.text = "Status: Success"
                        resultTextView.text = formattedResult.toString()
                    }
                } else {
                    throw Exception("HTTP Error: $responseCode")
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    progressBar.visibility = View.GONE
                    statusTextView.text = "Status: Error"
                    resultTextView.text = "Failed to fetch data: ${e.message}"
                }
            }
        }
    }
}