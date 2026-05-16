package com.example.assignment

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    private val TAG = "DetailActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        Log.d(TAG, "onCreate called")

        val displayTextView = findViewById<TextView>(R.id.receivedMessageTextView)
        val backBtn = findViewById<Button>(R.id.backButton)

        // Extract the data passed from MainActivity
        val receivedMessage = intent.getStringExtra("USER_MESSAGE_KEY")

        displayTextView.text = if (!receivedMessage.isNullOrEmpty()) {
            "Received: $receivedMessage"
        } else {
            "No message was sent."
        }

        backBtn.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop called")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy called")
    }
}