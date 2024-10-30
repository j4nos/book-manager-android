// HomeScreen.kt
package com.j4nos.bookmanagerandroid
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    books: List<Book>,
    onBookSelected: (Int) -> Unit,  // Callback to pass selected book index
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }

    // Filter books based on the search query
    val filteredBooks = books.filter { book ->
        book.title.contains(searchQuery, ignoreCase = true) || book.author.contains(searchQuery, ignoreCase = true)
    }

    Column(modifier = modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Search TextField
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search by title or author") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )

        // Display filtered list of books with clickable items
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(filteredBooks) { index, book ->
                BookItem(
                    book = book,
                    onClick = { onBookSelected(index) }  // Pass the index when clicked
                )
            }
        }
    }
}
