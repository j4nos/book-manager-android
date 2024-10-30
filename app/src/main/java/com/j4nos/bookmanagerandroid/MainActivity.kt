package com.j4nos.bookmanagerandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.j4nos.bookmanagerandroid.ui.theme.BookManagerAndroidTheme
import androidx.compose.runtime.*

data class Book(
    val id: String,
    val title: String,
    val author: String,
    val coverImage: String,
    val description: String,
    val isFavorite: Boolean
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BookManagerAndroidTheme {
                val bookViewModel = BookViewModel(this)
                var selectedIndex by remember { mutableStateOf(0) }
                var selectedBookIndex by remember { mutableStateOf<Int?>(null) }
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(
                            selectedIndex = selectedIndex,
                            onItemSelected = { index -> selectedIndex = index }
                        )
                    }
                ) { innerPadding ->
                    when (selectedIndex) {
                        0 -> {
                            if (selectedBookIndex != null) {
                                BookDetails(
                                    book = bookViewModel.books[selectedBookIndex!!],
                                    bookViewModel = bookViewModel,
                                    onBack = { selectedBookIndex = null },
                                )
                            } else {
                                HomeScreen(
                                    books = bookViewModel.books,
                                    onBookSelected = { index -> selectedBookIndex = index },
                                    modifier = Modifier.padding(innerPadding)
                                )
                            }
                        }

                        1 -> {
                            FavoritesScreen(
                                books = bookViewModel.books,
                                onBookSelected = { index -> selectedBookIndex = index }
                            )
                        }
                    }
                }
            }
        }
    }
}
