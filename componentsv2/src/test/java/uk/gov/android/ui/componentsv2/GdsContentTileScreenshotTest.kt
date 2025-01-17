package uk.gov.android.ui.componentsv2

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
internal class GdsContentTileScreenshotTest(
    private val parameters: Pair<GdsContentTilePreviewParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsContentTile(
            title = stringResource(parameters.title),
            onClick = {},
            image = parameters.image?.let { painterResource(it) },
            contentDescription = parameters.contentDescription?.let { stringResource(it) },
            showDismissIcon = parameters.showDismissIcon,
            caption = parameters.caption?.let { stringResource(it) },
            body = parameters.body?.let { stringResource(it) },
            displayPrimary = parameters.displayPrimary,
            buttonText = parameters.buttonText?.let { stringResource(it) },
            showSecondaryIcon = parameters.showSecondaryIcon,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsContentTileV2")
        fun values(): List<Pair<GdsContentTilePreviewParameters, NightMode>> {
            val result: MutableList<Pair<GdsContentTilePreviewParameters, NightMode>> = mutableListOf()

            GdsContentTilePreviewParametersProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
