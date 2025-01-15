package uk.gov.android.ui.componentsv2.button

import android.content.Context
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.R

class GdsButtonTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = ButtonParameterPreviewProvider().values.toList()
    private var onClick = 0

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        onClick = 0
    }

    @Test
    fun testPrimary() {
        setupContent(parameters[0])
        composeTestRule.onNodeWithText(
            resources.getString(R.string.primary_button),
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testSecondary() {
        setupContent(parameters[1])
        composeTestRule.onNodeWithText(
            resources.getString(R.string.secondary_button),
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testTertiary() {
        setupContent(parameters[2])
        composeTestRule.onNodeWithText(
            resources.getString(R.string.tertiary_button),
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testQuaternary() {
        setupContent(parameters[3])
        composeTestRule.onNodeWithText(
            resources.getString(R.string.quaternary_button),
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testAdmin() {
        setupContent(parameters[4])
        composeTestRule.onNodeWithText(
            resources.getString(R.string.admin_button),
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testError() {
        setupContent(parameters[5])
        composeTestRule.onNodeWithText(
            resources.getString(R.string.error_button),
        ).apply {
            assertIsDisplayed()
            performClick()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testIcon() {
        setupContent(parameters[6])
        composeTestRule.apply {
            onNodeWithText(
                resources.getString(R.string.text_button),
                substring = true,
            ).apply {
                assertIsDisplayed()
                performClick()
            }

            onNodeWithContentDescription(
                resources.getString(R.string.icon_content_desc),
            ).assertIsDisplayed()
        }

        assertEquals(1, onClick)
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            ButtonPreview(parameters[0])
        }
        composeTestRule.onNodeWithText(
            resources.getString(R.string.primary_button),
        ).assertIsDisplayed()
    }

    private fun setupContent(parameters: ButtonParameters) {
        composeTestRule.setContent {
            GdsButton(
                text = stringResource(parameters.text),
                buttonType = parameters.buttonType.toButtonType(),
                onClick = { onClick++ },
            )
        }
    }
}
