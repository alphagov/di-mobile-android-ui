package uk.gov.android.ui.componentsv2

import androidx.compose.runtime.Composable
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class GdsCardScreenshotTest(
    private val parameters: Pair<GdsCardParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        GdsCard(parameters.first) { }
    }

    companion object {
        @JvmStatic
        // TODO: Rename after screenshot comparison
        @Parameterized.Parameters(name = "{index}GdsContentTileV2")
        fun values(): List<Pair<GdsCardParameters, NightMode>> {
            val result: MutableList<Pair<GdsCardParameters, NightMode>> = mutableListOf()

            GdsCardPreviewParameters().values.forEach(applyNightMode(result))

            return result
        }
    }
}
