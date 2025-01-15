package uk.gov.android.ui.componentsv2.images

import android.content.Context
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.test.core.app.ApplicationProvider
import org.junit.Rule
import org.junit.Test
import uk.gov.android.ui.componentsv2.R

class GdsIconTest {
    private val context: Context = ApplicationProvider.getApplicationContext()
    private val resources = context.resources
    private val parameters = IconPreviewParameters().values.toList()[0]

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testIcon() {
        composeTestRule.setContent {
            GdsIcon(
                image = painterResource(parameters.image),
                modifier = parameters.modifier,
                color = parameters.color,
                backgroundColor = parameters.backgroundColor,
                contentDescription = stringResource(parameters.contentDescription),
                size = parameters.size,
            )
        }
        composeTestRule.onNodeWithContentDescription(
            resources.getString(R.string.icon_content_desc),
        ).assertIsDisplayed()
    }

    @Test
    fun testPreview() {
        composeTestRule.setContent {
            IconPreview(parameters)
        }
        composeTestRule.onNodeWithContentDescription(
            resources.getString(R.string.icon_content_desc),
        ).assertIsDisplayed()
    }
}
