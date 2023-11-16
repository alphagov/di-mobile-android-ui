package uk.gov.android.ui.components.m3.content

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import uk.gov.android.ui.components.content.GdsContentText
import uk.gov.android.ui.components.m3.Heading
import uk.gov.android.ui.theme.GdsTheme

@Composable
fun GdsContent(
    contentParameters: ContentParameters,
    colors: ColorScheme = MaterialTheme.colorScheme
) {
    Column(
        modifier = Modifier
            .background(
                color = colors.background
            )
            .then(
                contentParameters.modifier
            )

    ) {
        contentParameters.apply {
            this.resource.forEach { contentText ->
                Column(
                    modifier = internalColumnModifier
                ) {
                    contentText.subTitle?.let { subTitle ->
                        Heading(
                            modifier = headingModifier,
                            padding = PaddingValues(),
                            size = headingSize,
                            text = subTitle,
                            textAlign = textAlign
                        ).generate()
                    }
                    when (contentText) {
                        is GdsContentText.GdsContentTextString ->
                            contentText.text.map {
                                stringResource(id = it)
                            }.toTypedArray()

                        is GdsContentText.GdsContentTextArray ->
                            stringArrayResource(id = contentText.text)
                    }.forEach {
                        Text(
                            color = color ?: colors.contentColorFor(colors.background),
                            modifier = textModifier,
                            style = textStyle ?: MaterialTheme.typography.bodyLarge,
                            text = it,
                            textAlign = textAlign
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun Preview(
    @PreviewParameter(ContentProvider::class)
    contentParameters: ContentParameters
) {
    GdsTheme {
        GdsContent(
            contentParameters
        )
    }
}
