package uk.gov.android.ui.componentsv2

import android.content.Context
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithContentDescription
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GdsCardTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = GdsCardPreviewParameters().values.toList()
    private var onClick: Int = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        onClick = 0
    }

    @Test
    fun checkCardWithPrimaryBtnAndAllComponents() {
        setupContent(parameters[0])
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.vector_image_content_description),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.body),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsDisplayed()

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkCardWithSecondaryBtnAndTitle() {
        setupContent(parameters[1])
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.vector_image_content_description),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.secondary_button),
                substring = true,
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            // The same icon description is used for the AnnotatedString used for Secondary Button
            onAllNodesWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertCountEquals(1)

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkCardWithPrimaryBtnAndNoImage() {
        setupContent(parameters[2])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.body),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsNotDisplayed()

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkCardWithPrimaryBtnAndNoImageAndNoBody() {
        setupContent(parameters[3])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsNotDisplayed()

            assertEquals(1, onClick)
        }
    }

    @Test
    fun checkCardWithTitleAndCaption() {
        setupContent(parameters[4])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsNotDisplayed()

            assertEquals(0, onClick)
        }
    }

    @Test
    fun checkCardWithPrimaryBtnAndDismissBtnButNoImage() {
        setupContent(parameters[5])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.body),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsDisplayed()

            assertEquals(1, onClick)
        }
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            GdsCardPreview(parameters[0])
        }
        composeTestRule.apply {
            onNodeWithContentDescription(
                resources.getString(R.string.vector_image_content_description),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.caption),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.title),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.body),
            ).assertIsDisplayed()

            onNodeWithText(
                resources.getString(R.string.primary_button),
            ).assertIsDisplayed()
        }
    }

    private fun setupContent(parameters: GdsCardParameters) {
        composeTestRule.setContent {
            GdsCard(parameters) {
                onClick++
            }
        }
    }
}
