package com.app.testapi.presentation.ui.components.atoms

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.testapi.presentation.theme.TestAPITheme

@Suppress("ktlint:standard:function-naming")
@Composable
fun RowScope.CellButton(
    number: Int,
    isInitial: Boolean,
    isSelected: Boolean,
    onSelect: () -> Unit,
) {
    val backgroundColor =
        when {
            isInitial -> MaterialTheme.colorScheme.surfaceVariant
            isSelected -> MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)
            else -> MaterialTheme.colorScheme.surface
        }

    Surface(
        modifier =
            Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(0.5.dp)
                .clickable(onClick = onSelect),
        color = backgroundColor,
        shape = RoundedCornerShape(2.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f)),
    ) {
        Box(contentAlignment = Alignment.Center) {
            if (number != 0) {
                Text(
                    text = number.toString(),
                    color = MaterialTheme.colorScheme.onSurface,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                )
            }
        }
    }
}
