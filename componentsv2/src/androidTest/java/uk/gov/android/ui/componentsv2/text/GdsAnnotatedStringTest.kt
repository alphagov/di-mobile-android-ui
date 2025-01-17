package uk.gov.android.ui.componentsv2.text

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.R

class GdsAnnotatedStringTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = AnnotatedStringPreviewParameters().values.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testStringIconTrailing() {
        composeTestRule.setContent {
            GdsAnnotatedString(parameters[0])
        }
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.annotated_string),
                substring = true,
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsDisplayed()
        }
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            AnnotatedStringPreview(parameters[0])
        }
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.annotated_string),
                substring = true,
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsDisplayed()
        }
    }
}
