import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.j4nos.bookmanagerandroid.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BookManagerUITests {

    // Use createAndroidComposeRule to manage MainActivity
    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun testAddBookToFavoritesAndCheckFavorites() {
        // Locate the book by title and tap to open details
        composeTestRule.onNodeWithText("1984").assertExists().performClick()

        // Mark as favorite
        composeTestRule.onNodeWithText("Add to Favorites").assertExists().performClick()

        // Navigate back to the main list
        composeTestRule.onNodeWithContentDescription("Back").performClick()

        // Switch to the Favorites tab
        composeTestRule.onNodeWithText("Favorites").performClick()

        // Check that the favorite book appears
        composeTestRule.onNodeWithText("1984").assertExists()
    }
}
