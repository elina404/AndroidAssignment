package com.example.assignment

import android.content.Context
import android.os.Bundle
import android.util.Base64
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader

class StorageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_storage)

        val prefEditText = findViewById<EditText>(R.id.prefEditText)
        val btnSavePref = findViewById<Button>(R.id.btnSavePref)
        val btnLoadPref = findViewById<Button>(R.id.btnLoadPref)
        val fileEditText = findViewById<EditText>(R.id.fileEditText)
        val btnWriteFile = findViewById<Button>(R.id.btnWriteFile)
        val btnReadFile = findViewById<Button>(R.id.btnReadFile)
        val displayTextView = findViewById<TextView>(R.id.displayDataTextView)

        val sharedPref = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)

        // Chapter 8 Task 4: Secure user data using basic encryption
        // We will encrypt the text before saving it to SharedPreferences
        
        btnSavePref.setOnClickListener {
            val originalText = prefEditText.text.toString()
            if (originalText.isNotEmpty()) {
                val encryptedText = encrypt(originalText)
                sharedPref.edit().putString("secure_text", encryptedText).apply()
                Toast.makeText(this, "Encrypted & Saved", Toast.LENGTH_SHORT).show()
            }
        }

        btnLoadPref.setOnClickListener {
            val encryptedText = sharedPref.getString("secure_text", "")
            if (!encryptedText.isNullOrEmpty()) {
                val decryptedText = decrypt(encryptedText)
                displayTextView.text = "Decrypted: $decryptedText\n(Raw: $encryptedText)"
            } else {
                displayTextView.text = "No data found"
            }
        }

        // Chapter 4 Task 2: Internal Storage
        val fileName = "example_file.txt"
        btnWriteFile.setOnClickListener {
            val content = fileEditText.text.toString()
            try {
                val fos: FileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE)
                fos.write(content.toByteArray())
                fos.close()
                Toast.makeText(this, "Saved to File", Toast.LENGTH_SHORT).show()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        btnReadFile.setOnClickListener {
            try {
                val fis: FileInputStream = openFileInput(fileName)
                val isr = InputStreamReader(fis)
                val br = BufferedReader(isr)
                val sb = StringBuilder()
                var text: String?
                while (br.readLine().also { text = it } != null) {
                    sb.append(text)
                }
                displayTextView.text = "From File: $sb"
                fis.close()
            } catch (e: Exception) {
                displayTextView.text = "File not found"
            }
        }
    }

    // Basic Base64 Encryption (Chapter 8 Task 4 Demonstration)
    private fun encrypt(input: String): String {
        return Base64.encodeToString(input.toByteArray(), Base64.DEFAULT)
    }

    private fun decrypt(input: String): String {
        return String(Base64.decode(input, Base64.DEFAULT))
    }
}