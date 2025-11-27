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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.testapi.presentation.ui.components.atoms.PrimaryButton

@Suppress("ktlint:standard:function-naming")
@Composable
fun HomeScreen(viewModel: HomeViewModel = viewModel()) {
    val initial4x4Grid =
        listOf(
            1,
            2,
            0,
            4,
            3,
            4,
            1,
            2,
            0,
            1,
            4,
            3,
            4,
            3,
            2,
            1,
        )

    val initial9x9Grid =
        listOf(
            5,
            3,
            0,
            0,
            7,
            0,
            0,
            0,
            0,
            6,
            0,
            0,
            1,
            9,
            5,
            0,
            0,
            0,
            0,
            9,
            8,
            0,
            0,
            0,
            0,
            6,
            0,
            8,
            0,
            0,
            0,
            6,
            0,
            0,
            0,
            3,
            4,
            0,
            0,
            8,
            0,
            3,
            0,
            0,
            1,
            7,
            0,
            0,
            0,
            2,
            0,
            0,
            0,
            6,
            0,
            6,
            0,
            0,
            0,
            0,
            2,
            8,
            0,
            0,
            0,
            0,
            4,
            1,
            9,
            0,
            0,
            5,
            0,
            0,
            0,
            0,
            8,
            0,
            0,
            7,
            9,
        )

    val solution4x4 =
        listOf(
            1,
            2,
            3,
            4,
            3,
            4,
            1,
            2,
            2,
            1,
            4,
            3,
            4,
            3,
            2,
            1,
        )

    val solution9x9 =
        listOf(
            5,
            3,
            4,
            6,
            7,
            8,
            9,
            1,
            2,
            6,
            7,
            2,
            1,
            9,
            5,
            3,
            4,
            8,
            1,
            9,
            8,
            3,
            4,
            2,
            5,
            6,
            7,
            8,
            5,
            9,
            7,
            6,
            1,
            4,
            2,
            3,
            4,
            2,
            6,
            8,
            5,
            3,
            7,
            9,
            1,
            7,
            1,
            3,
            9,
            2,
            4,
            8,
            5,
            6,
            9,
            6,
            1,
            5,
            3,
            7,
            2,
            8,
            4,
            2,
            8,
            7,
            4,
            1,
            9,
            6,
            3,
            5,
            3,
            4,
            5,
            2,
            8,
            6,
            1,
            7,
            9,
        )
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
                onClick = { },
            )
            PrimaryButton(
                text = "9x9",
                onClick = { },
            )
        }
    }
}
