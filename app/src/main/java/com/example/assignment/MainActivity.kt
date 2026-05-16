package com.example.assignment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivityLifecycle"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate called")

        val messageInput = findViewById<EditText>(R.id.messageEditText)
        val navigateBtn = findViewById<Button>(R.id.navigateToDetailButton)
        val loginBtn = findViewById<Button>(R.id.navigateToLoginButton)
        val profileBtn = findViewById<Button>(R.id.navigateToProfileButton)
        val storageBtn = findViewById<Button>(R.id.navigateToStorageButton)
        val notesBtn = findViewById<Button>(R.id.navigateToNotesButton)
        val networkBtn = findViewById<Button>(R.id.navigateToNetworkButton)
        val recyclerBtn = findViewById<Button>(R.id.navigateToRecyclerButton)
        val fragmentBtn = findViewById<Button>(R.id.navigateToFragmentButton)
        val multimediaBtn = findViewById<Button>(R.id.navigateToMultimediaButton)

        // Chapter 2 & 3: Lifecycle & Multi-screen Navigation
        navigateBtn.setOnClickListener {
            val message = messageInput.text.toString()
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("USER_MESSAGE_KEY", message)
            startActivity(intent)
        }

        // Chapter 3: Login Form
        loginBtn.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Chapter 3: ConstraintLayout Profile
        profileBtn.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Chapter 4: SharedPreferences & Files
        storageBtn.setOnClickListener {
            startActivity(Intent(this, StorageActivity::class.java))
        }

        // Chapter 4: SQLite Notes
        notesBtn.setOnClickListener {
            startActivity(Intent(this, NotesActivity::class.java))
        }

        // Chapter 5: Networking
        networkBtn.setOnClickListener {
            startActivity(Intent(this, NetworkActivity::class.java))
        }

        // Chapter 6: RecyclerView & ViewModel
        recyclerBtn.setOnClickListener {
            startActivity(Intent(this, RecyclerActivity::class.java))
        }

        // Chapter 6: Fragments
        fragmentBtn.setOnClickListener {
            startActivity(Intent(this, FragmentActivity::class.java))
        }

        // Chapter 7: Multimedia & Sensors
        multimediaBtn.setOnClickListener {
            startActivity(Intent(this, MultimediaActivity::class.java))
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