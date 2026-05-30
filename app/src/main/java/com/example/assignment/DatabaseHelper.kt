package com.example.assignment

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "NotesDB"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NOTES = "notes"
        private const val KEY_ID = "id"
        private const val KEY_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_NOTES + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_CONTENT + " TEXT" + ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NOTES")
        onCreate(db)
    }

    // CREATE
    fun addNote(content: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_CONTENT, content)
        val id = db.insert(TABLE_NOTES, null, values)
        db.close()
        return id
    }

    // READ
    fun getAllNotes(): List<String> {
        val notesList = mutableListOf<String>()
        val selectQuery = "SELECT * FROM $TABLE_NOTES"
        val db = this.readableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        if (cursor.moveToFirst()) {
            do {
                val content = cursor.getString(cursor.getColumnIndexOrThrow(KEY_CONTENT))
                notesList.add(content)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return notesList
    }

    // DELETE ALL
    fun deleteAllNotes() {
        val db = this.writableDatabase
        db.delete(TABLE_NOTES, null, null)
        db.close()
    }

    // UPDATE (Chapter 4 Task 3 Complete CRUD)
    fun updateNote(oldContent: String, newContent: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(KEY_CONTENT, newContent)
        val result = db.update(TABLE_NOTES, values, "$KEY_CONTENT=?", arrayOf(oldContent))
        db.close()
        return result
    }
}