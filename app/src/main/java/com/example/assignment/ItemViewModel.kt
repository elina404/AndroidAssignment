package com.example.assignment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemViewModel : ViewModel() {

    // Chapter 6 Task 5: Use ViewModel and LiveData to manage UI data
    private val _items = MutableLiveData<List<ItemModel>>()
    val items: LiveData<List<ItemModel>> get() = _items

    init {
        // Initial data
        _items.value = listOf(
            ItemModel("Kotlin", "Modern programming language for Android"),
            ItemModel("Jetpack Compose", "Modern toolkit for building native UI"),
            ItemModel("Retrofit", "Type-safe HTTP client for Android")
        )
    }

    fun addItem() {
        val currentList = _items.value?.toMutableList() ?: mutableListOf()
        val nextId = currentList.size + 1
        currentList.add(ItemModel("New Item $nextId", "Description for item $nextId"))
        _items.value = currentList
    }
}