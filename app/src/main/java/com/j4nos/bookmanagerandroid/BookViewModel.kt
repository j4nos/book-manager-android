// BookViewModel.kt
package com.j4nos.bookmanagerandroid

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

class BookViewModel(context: Context) {
    private val sharedPreferences =
        context.getSharedPreferences("BookPreferences", Context.MODE_PRIVATE)
    private val booksKey = "books"

    var books by mutableStateOf<List<Book>>(emptyList())
        private set

    init {
        loadBooks(context)
    }

    private fun loadBooks(context: Context) {
        val json = sharedPreferences.getString(booksKey, null)

        books = if (json != null) {
            val type = object : TypeToken<List<Book>>() {}.type
            Gson().fromJson(json, type)
        } else {
            loadBooksFromAssets(context).also { saveBooks(it) }
        }
    }

    private fun saveBooks(booksToSave: List<Book>) {
        val json = Gson().toJson(booksToSave)
        sharedPreferences.edit().putString(booksKey, json).apply()
    }

    fun toggleFavorite(bookId: String) {
        books = books.map {
            if (it.id == bookId) it.copy(isFavorite = !it.isFavorite) else it
        }
        saveBooks(books)  // Save the updated list to SharedPreferences
    }

    private fun loadBooksFromAssets(context: Context): List<Book> {
        return try {
            val json = context.assets.open("Books.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Book>>() {}.type
            Gson().fromJson(json, type)
        } catch (ex: IOException) {
            ex.printStackTrace()
            emptyList()
        }
    }
}
