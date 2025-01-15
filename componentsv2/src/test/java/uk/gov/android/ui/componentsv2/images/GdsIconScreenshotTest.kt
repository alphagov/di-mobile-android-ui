package uk.gov.android.ui.componentsv2.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsIconScreenshotTest(
    private val parameters: Pair<IconParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsIcon(
            image = painterResource(parameters.image),
            modifier = parameters.modifier,
            color = parameters.color,
            backgroundColor = parameters.backgroundColor,
            contentDescription = stringResource(parameters.contentDescription),
            size = parameters.size,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsIconV2")
        fun values(): List<Pair<IconParameters, NightMode>> {
            val result: MutableList<Pair<IconParameters, NightMode>> = mutableListOf()

            IconPreviewParameters().values.forEach(applyNightMode(result))

            return result
        }
    }
}
