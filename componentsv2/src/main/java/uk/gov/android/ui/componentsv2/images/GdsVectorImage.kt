package uk.gov.android.ui.componentsv2.images

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.R
import uk.gov.android.ui.theme.GdsTheme

@Composable
fun GdsVectorImage(
    image: Painter,
    modifier: Modifier = Modifier,
    color: Color = Color.Unspecified,
    contentDescription: String,
    scale: ContentScale = ContentScale.Fit,
) {
    Image(
        painter = key(image) { image },
        colorFilter = color.toTint(),
        contentDescription = contentDescription,
        contentScale = scale,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
    )
}

data class VectorImageParameters(
    @DrawableRes
    val image: Int,
    val color: Color = Color.Unspecified,
    @StringRes
    val contentDescription: Int,
    val scale: ContentScale = ContentScale.Fit,
    val modifier: Modifier = Modifier,
)

private fun Color.toTint(): ColorFilter? = if (this != Color.Unspecified) {
    ColorFilter.tint(this)
} else {
    null
}

class VectorImageProvider : PreviewParameterProvider<VectorImageParameters> {
    override val values: Sequence<VectorImageParameters> = sequenceOf(
        VectorImageParameters(
            contentDescription = R.string.vector_image_content_description,
            image = R.drawable.ic_vector_image,
            color = Color.Black,
        ),
        VectorImageParameters(
            contentDescription = R.string.vector_image_content_description,
            image = R.drawable.ic_vector_image,
        ),
    )
}

@Composable
@PreviewLightDark
fun VectorImagePreview(
    @PreviewParameter(VectorImageProvider::class)
    parameters: VectorImageParameters,
) {
    GdsTheme {
        GdsVectorImage(
            image = painterResource(parameters.image),
            modifier = parameters.modifier,
            color = parameters.color,
            contentDescription = stringResource(parameters.contentDescription),
            scale = parameters.scale,
        )
    }
}
