package uk.gov.ui.components.content

import android.content.Context
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import uk.gov.ui.theme.GdsTheme

@RunWith(AndroidJUnit4::class)
class LinkTextTest {
    private val context: Context = ApplicationProvider.getApplicationContext()

    private val resources = context.resources

    private val expectedParameterSize = 1
    private val parameterList = LinkTextProvider().values.toList()

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        Assert.assertEquals(
            "The expected size of the provider has changed!",
            expectedParameterSize,
            parameterList.size
        )
    }

    @Test
    fun verifyFirstParameters() = linkTextTests(parameterList[0])

    private fun linkTextTests(parameters: LinkTextParameters) {
        composeTestRule.apply {
            setContent {
                GdsTheme {
                    GdsLinkText(
                        parameters
                    )
                }
            }

            onNodeWithText(resources.getString(parameters.contentText)).apply {
                assertIsDisplayed()
            }
        }
    }
}
