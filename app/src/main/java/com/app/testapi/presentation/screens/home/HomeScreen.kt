package com.app.testapi.presentation.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.testapi.presentation.ui.components.atoms.PrimaryButton

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(onGameSelected: (String) -> Unit) {
    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "SUDOKU APP",
            style = MaterialTheme.typography.displayLarge.copy(fontWeight = FontWeight.Black),
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 60.dp),
        )

        Text(
            text = "¿Qué tamaño quieres jugar?",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            PrimaryButton(
                text = "4x4",
                onClick = { onGameSelected("4") },
            )
            PrimaryButton(
                text = "9x9",
                onClick = { onGameSelected("9") },
            )
        }
    }
}
