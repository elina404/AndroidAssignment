package com.example.assignment

import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RecyclerActivity : AppCompatActivity() {

    // Chapter 6 Task 5: Use ViewModel
    private val viewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnAddItem = findViewById<Button>(R.id.btnAddItem)

        // Chapter 6 Task 2 & 3: RecyclerView and Custom Adapter
        val adapter = ItemAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Observing LiveData from ViewModel
        viewModel.items.observe(this) { items ->
            adapter.updateItems(items)
        }

        btnAddItem.setOnClickListener {
            viewModel.addItem()
        }
    }
}