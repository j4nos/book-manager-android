// FavoritesScreen.kt
package com.j4nos.bookmanagerandroid

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FavoritesScreen(
    books: List<Book>,
    onBookSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteBooks = books.filter { it.isFavorite }
    if (favoriteBooks.isNotEmpty()) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(favoriteBooks) { book ->
                BookItem(
                    book = book,
                    onClick = { onBookSelected(books.indexOf(book)) } // Pass index from original list
                )
            }
        }
    } else {
        // Show a message if no favorites
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No favorite books found")
        }
    }
}
