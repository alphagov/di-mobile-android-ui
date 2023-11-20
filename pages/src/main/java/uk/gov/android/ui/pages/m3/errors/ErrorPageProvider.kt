package uk.gov.android.ui.pages.m3.errors

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import uk.gov.android.ui.components.R.drawable
import uk.gov.android.ui.components.R.string
import uk.gov.android.ui.components.content.GdsContentText.GdsContentTextString
import uk.gov.android.ui.components.images.icon.IconParameters
import uk.gov.android.ui.components.m3.HeadingSize
import uk.gov.android.ui.components.m3.buttons.ButtonParameters
import uk.gov.android.ui.components.m3.buttons.ButtonType
import uk.gov.android.ui.components.m3.content.ContentParameters
import uk.gov.android.ui.components.m3.information.InformationParameters

class ErrorPageProvider : PreviewParameterProvider<ErrorPageParameters> {
    override val values: Sequence<ErrorPageParameters> = sequenceOf(
        ErrorPageParameters(
            primaryButtonParameters = ButtonParameters(
                buttonType = ButtonType.PRIMARY(),
                onClick = {},
                text = string.preview__GdsButton__primary
            ),
            informationParameters = InformationParameters(
                contentParameters = ContentParameters(
                    resource = listOf(
                        GdsContentTextString(
                            text = arrayOf(string.preview__GdsContent__oneLine_0).toIntArray()
                        )
                    )
                ),
                iconParameters = IconParameters(
                    foreGroundColor = Color.Unspecified,
                    image = drawable.ic_error
                )
            )
        ),
        ErrorPageParameters(
            primaryButtonParameters = ButtonParameters(
                buttonType = ButtonType.PRIMARY(),
                onClick = {},
                text = string.preview__GdsButton__primary
            ),
            secondaryButtonParameters = ButtonParameters(
                buttonType = ButtonType.ICON(
                    buttonType = ButtonType.SECONDARY(),
                    iconParameters = IconParameters(
                        foreGroundColor = Color.Unspecified,
                        image = drawable.ic_external_site
                    )
                ),
                onClick = {},
                text = string.preview__GdsButton__secondary
            ),
            informationParameters = InformationParameters(
                contentParameters = ContentParameters(
                    resource = listOf(
                        GdsContentTextString(
                            text = arrayOf(string.preview__GdsContent__oneLine_0).toIntArray()
                        )
                    )
                ),
                iconParameters = IconParameters(
                    foreGroundColor = Color.Unspecified,
                    image = drawable.ic_error
                )
            )
        ),
        ErrorPageParameters(
            primaryButtonParameters = ButtonParameters(
                buttonType = ButtonType.PRIMARY(),
                onClick = {},
                text = string.preview__GdsButton__primary
            ),
            secondaryButtonParameters = ButtonParameters(
                buttonType = ButtonType.ICON(
                    buttonType = ButtonType.SECONDARY(),
                    iconParameters = IconParameters(
                        foreGroundColor = Color.Unspecified,
                        image = drawable.ic_external_site
                    )
                ),
                onClick = {},
                text = string.preview__GdsButton__secondary
            ),
            informationParameters = InformationParameters(
                contentParameters = ContentParameters(
                    resource = listOf(
                        GdsContentTextString(
                            subTitle = string.preview__GdsHeading__subTitle1,
                            text = arrayOf(string.preview__GdsContent__oneLine_0).toIntArray()
                        )
                    ),
                    headingSize = HeadingSize.H1()
                ),
                iconParameters = IconParameters(
                    foreGroundColor = Color.Unspecified,
                    image = drawable.ic_error
                )
            )
        )
    )
}
