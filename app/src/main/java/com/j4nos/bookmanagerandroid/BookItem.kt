// BookItem.kt
package com.j4nos.bookmanagerandroid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import androidx.compose.ui.Alignment
import androidx.compose.material3.MaterialTheme.typography

@Composable
fun BookItem(book: Book, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },  // Handle click
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = book.coverImage,
            contentDescription = book.title,
            modifier = Modifier.size(64.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column {
            Text(text = book.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = "by ${book.author}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
