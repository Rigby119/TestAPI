package com.app.testapi.presentation.ui.components.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.app.testapi.presentation.theme.TestAPITheme

@Suppress("ktlint:standard:function-naming")
@Composable
fun NumberSquareButton(
    number: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    cornerRadius: Dp = 8.dp,
    borderStroke: BorderStroke? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    selectedBackgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    selectedContentColor: Color = MaterialTheme.colorScheme.onPrimary,
) {
    val currentBackgroundColor = if (isSelected) selectedBackgroundColor else backgroundColor
    val currentContentColor = if (isSelected) selectedContentColor else contentColor

    Surface(
        onClick = onClick,
        modifier =
            modifier
                .aspectRatio(1f)
                .fillMaxHeight(),
        shape = RoundedCornerShape(cornerRadius),
        color = currentBackgroundColor,
        border = borderStroke,
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.padding(4.dp),
        ) {
            Text(
                text = number.toString(),
                style =
                    MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.Bold,
                        color = currentContentColor,
                    ),
            )
        }
    }
}
