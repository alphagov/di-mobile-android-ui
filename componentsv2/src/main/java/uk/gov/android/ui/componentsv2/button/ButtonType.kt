package uk.gov.android.ui.componentsv2.button

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import uk.gov.android.ui.theme.adminButton
import uk.gov.android.ui.theme.m3_disabled
import uk.gov.android.ui.theme.m3_onDisabled

sealed class ButtonType(
    open val fontWeight: FontWeight = FontWeight.Bold,
) {
    open class Primary : ButtonType()

    open class Secondary : ButtonType(
        fontWeight = FontWeight.Light,
    )

    open class Tertiary : ButtonType()

    open class Quaternary : ButtonType(
        fontWeight = FontWeight.Light,
    )

    open class Admin : ButtonType()

    open class Error : ButtonType()

    open class Custom(
        val contentColor: Color,
        val containerColor: Color,
        fontWeight: FontWeight = FontWeight.Light,
    ) : ButtonType(
        fontWeight = fontWeight,
    )

    data class Icon(
        val buttonColors: ButtonColors,
        val iconImage: Painter,
        val contentDescription: String,
        override val fontWeight: FontWeight = FontWeight.Light,
        val isIconTrailing: Boolean = true,
    ) : ButtonType(
        fontWeight = fontWeight,
    )
}

@Composable
internal fun ButtonType.buttonColors() = when (this) {
    is ButtonType.Admin -> adminButtonColors()
    is ButtonType.Custom -> customButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    is ButtonType.Error -> errorButtonColors()
    is ButtonType.Icon -> buttonColors
    is ButtonType.Primary -> primaryButtonColors()
    is ButtonType.Quaternary -> quaternaryButtonColors()
    is ButtonType.Secondary -> secondaryButtonColors()
    is ButtonType.Tertiary -> tertiaryButtonColors()
}

@Composable
internal fun primaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.primary,
    contentColor = colorScheme.onPrimary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled,
)

@Composable
internal fun secondaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.secondary,
    contentColor = colorScheme.primary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled,
)

@Composable
internal fun tertiaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.tertiary,
    contentColor = colorScheme.onTertiary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled,
)

@Composable
internal fun quaternaryButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.secondary,
    contentColor = colorScheme.onSecondary,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled,
)

@Composable
internal fun adminButtonColors() = ButtonDefaults.buttonColors(
    containerColor = adminButton,
    contentColor = Color.White,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled,
)

@Composable
internal fun errorButtonColors() = ButtonDefaults.buttonColors(
    containerColor = colorScheme.error,
    contentColor = colorScheme.onError,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled,
)

@Composable
fun customButtonColors(containerColor: Color, contentColor: Color) = ButtonDefaults.buttonColors(
    contentColor = contentColor,
    containerColor = containerColor,
    disabledContainerColor = m3_disabled,
    disabledContentColor = m3_onDisabled,
)
