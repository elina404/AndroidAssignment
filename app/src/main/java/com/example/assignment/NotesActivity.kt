package com.example.assignment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class NotesActivity : AppCompatActivity() {

    private lateinit var dbHelper: DatabaseHelper
    private lateinit var noteEditText: EditText
    private lateinit var notesDisplayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        dbHelper = DatabaseHelper(this)
        noteEditText = findViewById(R.id.noteEditText)
        notesDisplayTextView = findViewById(R.id.notesDisplayTextView)
        val btnAddNote = findViewById<Button>(R.id.btnAddNote)
        val btnClearNotes = findViewById<Button>(R.id.btnClearNotes)

        // Task 3 & 4: SQLite CRUD Operations
        btnAddNote.setOnClickListener {
            val noteContent = noteEditText.text.toString().trim()
            if (noteContent.isNotEmpty()) {
                val id = dbHelper.addNote(noteContent)
                if (id != -1L) {
                    Toast.makeText(this, "Note saved!", Toast.LENGTH_SHORT).show()
                    noteEditText.text.clear()
                    displayNotes()
                } else {
                    Toast.makeText(this, "Error saving note", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please enter a note", Toast.LENGTH_SHORT).show()
            }
        }

        btnClearNotes.setOnClickListener {
            dbHelper.deleteAllNotes()
            displayNotes()
            Toast.makeText(this, "All notes cleared", Toast.LENGTH_SHORT).show()
        }

        displayNotes()
    }

    private fun displayNotes() {
        val notes = dbHelper.getAllNotes()
        val sb = StringBuilder()
        for (note in notes) {
            sb.append("• ").append(note).append("\n\n")
        }
        notesDisplayTextView.text = if (sb.isEmpty()) "No notes yet." else sb.toString()
    }
}