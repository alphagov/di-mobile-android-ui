package uk.gov.android.ui.componentsv2.images

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsVectorImageScreenshotTest(
    private val parameters: Pair<VectorImagePreviewParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsVectorImage(
            image = ImageVector.vectorResource(parameters.image),
            modifier = parameters.modifier,
            color = parameters.color,
            contentDescription = stringResource(parameters.contentDescription),
            scale = parameters.scale,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsVectorImageV2")
        fun values(): List<Pair<VectorImagePreviewParameters, NightMode>> {
            val result: MutableList<Pair<VectorImagePreviewParameters, NightMode>> = mutableListOf()

            VectorImageProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
