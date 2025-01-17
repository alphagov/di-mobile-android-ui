package uk.gov.android.ui.componentsv2.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.android.resources.NightMode
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import uk.gov.android.ui.componentsv2.BaseScreenshotTest

@RunWith(Parameterized::class)
internal class GdsButtonScreenshotTest(
    private val parameters: Pair<ButtonParameters, NightMode>,
) : BaseScreenshotTest(parameters.second) {

    override val generateComposeLayout: @Composable () -> Unit = {
        val parameters = parameters.first
        GdsButton(
            text = stringResource(parameters.text),
            buttonType = parameters.buttonType.toButtonType(),
            onClick = {},
            modifier = parameters.modifier,
            contentPosition = parameters.contentPosition,
            contentModifier = parameters.contentModifier,
            enabled = parameters.enabled,
        )
    }

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{index}GdsButtonV2")
        fun values(): List<Pair<ButtonParameters, NightMode>> {
            val result: MutableList<Pair<ButtonParameters, NightMode>> = mutableListOf()

            ButtonParameterPreviewProvider().values.forEach(applyNightMode(result))

            return result
        }
    }
}
