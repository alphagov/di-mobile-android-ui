package uk.gov.android.ui.componentsv2

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.componentsv2.button.ButtonType
import uk.gov.android.ui.componentsv2.button.GdsButton
import uk.gov.android.ui.componentsv2.button.customButtonColors
import uk.gov.android.ui.componentsv2.images.GdsVectorImage
import uk.gov.android.ui.componentsv2.utils.ModifierExtensions.customTilePadding
import uk.gov.android.ui.componentsv2.utils.ModifierExtensions.elevatedCardModifier
import uk.gov.android.ui.theme.ROW_DISTRIBUTION
import uk.gov.android.ui.theme.dividerThickness
import uk.gov.android.ui.theme.largePadding
import uk.gov.android.ui.theme.m3.GdsTheme
import uk.gov.android.ui.theme.m3.Typography
import uk.gov.android.ui.theme.smallPadding
import uk.gov.android.ui.theme.tileCornerRadius
import uk.gov.android.ui.theme.xsmallPadding

@Composable
fun GdsCard(
    title: String,
    onClick: () -> Unit,
    image: Painter? = null,
    contentDescription: String? = null,
    showDismissIcon: Boolean = false,
    caption: String? = null,
    body: String? = null,
    displayPrimary: Boolean = true,
    buttonText: String? = null,
    showSecondaryIcon: Boolean,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            contentColor = MaterialTheme.colorScheme.onBackground,
        ),
        shape = RoundedCornerShape(tileCornerRadius),
        modifier = Modifier.elevatedCardModifier(),
    ) {
        TileImage(
            image = image,
            contentDescription = contentDescription,
            showDismissIcon = showDismissIcon,
        )
        Column(
            modifier = Modifier
                .weight(1f, false),
        ) {
            Row {
                Column(
                    modifier = Modifier
                        .weight(ROW_DISTRIBUTION, false)
                        .padding(horizontal = smallPadding),
                ) {
                    caption?.let {
                        Text(
                            text = caption,
                            style = Typography.bodySmall,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = xsmallPadding),
                        )
                    }
                    Text(
                        text = title,
                        style = Typography.headlineMedium,
                        modifier = Modifier.customTilePadding(body != null),
                    )
                    body?.let {
                        Text(
                            text = body,
                            style = Typography.bodyLarge,
                            modifier = Modifier.padding(vertical = xsmallPadding),
                        )
                    }
                }
                if (image == null && showDismissIcon) {
                    DismissIcon()
                }
            }
            Buttons(
                text = buttonText,
                displayPrimary = displayPrimary,
                showSecondaryIcon = showSecondaryIcon,
                onClick = onClick,
            )
        }
    }
}

@Composable
private fun TileImage(
    image: Painter? = null,
    contentDescription: String? = null,
    showDismissIcon: Boolean,
) {
    val defaultContentDescription = stringResource(R.string.vector_image_content_description)
    image?.let {
        Box {
            GdsVectorImage(
                image = image,
                contentDescription = contentDescription ?: defaultContentDescription,
                scale = ContentScale.FillWidth,
            )
            Column(
                horizontalAlignment = Alignment.End,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                if (showDismissIcon) {
                    DismissIcon()
                }
            }
        }
    }
}

@Composable
private fun DismissIcon() {
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = stringResource(R.string.icon_content_desc),
        tint = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(
            top = xsmallPadding,
            bottom = largePadding,
            end = smallPadding,
        ),
    )
}

@Composable
private fun Buttons(
    text: String?,
    displayPrimary: Boolean,
    showSecondaryIcon: Boolean,
    onClick: () -> Unit,
) {
    text?.let {
        if (displayPrimary) {
            GdsButton(
                text = text,
                buttonType = ButtonType.Primary(),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = xsmallPadding,
                        bottom = smallPadding,
                        start = smallPadding,
                        end = smallPadding,
                    ),
                contentModifier = Modifier.fillMaxWidth(),
                onClick = onClick,
            )
        } else {
            if (showSecondaryIcon) {
                HorizontalDivider(
                    thickness = dividerThickness,
                    color = MaterialTheme.colorScheme.surface,
                )
                GdsButton(
                    text = text,
                    onClick = onClick,
                    buttonType = ButtonType.Icon(
                        buttonColors = customButtonColors(
                            contentColor = MaterialTheme.colorScheme.primary,
                            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
                        ),
                        iconImage = painterResource(R.drawable.ic_external_site),
                        contentDescription = stringResource(R.string.icon_content_desc),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = xsmallPadding),
                    contentPosition = Arrangement.Start,
                )
            }
        }
    }
}

internal data class GdsCardParameters(
    @DrawableRes
    val image: Int? = null,
    @StringRes
    val contentDescription: Int? = null,
    val showDismissIcon: Boolean = false,
    @StringRes
    val caption: Int? = null,
    @StringRes
    val title: Int,
    @StringRes
    val body: Int? = null,
    val displayPrimary: Boolean = true,
    val buttonText: Int? = null,
    val showSecondaryIcon: Boolean = false,
)

internal class GdsCardPreviewParameters : PreviewParameterProvider<GdsCardParameters> {
    override val values: Sequence<GdsCardParameters> = sequenceOf(
        GdsCardParameters(
            image = R.drawable.ic_tile_image,
            contentDescription = R.string.vector_image_content_description,
            showDismissIcon = true,
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            buttonText = R.string.primary_button,
        ),
        GdsCardParameters(
            image = R.drawable.ic_tile_image,
            title = R.string.title,
            displayPrimary = false,
            buttonText = R.string.secondary_button,
            showSecondaryIcon = true,
        ),
        GdsCardParameters(
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            displayPrimary = true,
            buttonText = R.string.primary_button,
        ),
        GdsCardParameters(
            caption = R.string.caption,
            title = R.string.title,
            displayPrimary = true,
            buttonText = R.string.primary_button,
        ),
        GdsCardParameters(
            caption = R.string.caption,
            title = R.string.title,
        ),
        GdsCardParameters(
            showDismissIcon = true,
            caption = R.string.caption,
            title = R.string.title,
            body = R.string.body,
            displayPrimary = true,
            buttonText = R.string.primary_button,
        ),
    )
}

@Composable
@PreviewLightDark
internal fun GdsCardPreview(
    @PreviewParameter(GdsCardPreviewParameters::class)
    parameters: GdsCardParameters,
) {
    GdsTheme {
        GdsCard(
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
}
