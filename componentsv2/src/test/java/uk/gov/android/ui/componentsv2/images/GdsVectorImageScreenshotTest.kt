package uk.gov.android.ui.componentsv2.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsVectorImageScreenshotTest(
    private val parameters: Pair<VectorImageParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsVectorImage(
            image = painterResource(parameters.image),
            modifier = parameters.modifier,
            color = parameters.color,
            contentDescription = stringResource(parameters.contentDescription),
            scale = parameters.scale,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsVectorImageV2")
        fun values(): List<Pair<VectorImageParameters, NightMode>> {
            val result: MutableList<Pair<VectorImageParameters, NightMode>> = mutableListOf()

            VectorImageProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
