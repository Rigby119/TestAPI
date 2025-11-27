package com.app.testapi.presentation.screens.game

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.app.testapi.presentation.ui.components.atoms.NumberSquareButton
import com.app.testapi.presentation.ui.components.atoms.PrimaryButton
import com.app.testapi.presentation.ui.components.molecules.SudokuGrid

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
// 0, 1, 2, 3

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

// Soluciones (simplificadas para revisión rápida)
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

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameScreen(
    size: Int,
    onBackToHome: () -> Unit,
) {
    val initialGrid =
        remember(size) {
            if (size == 4) initial4x4Grid else initial9x9Grid
        }
    var gridState by remember(size) { mutableStateOf(initialGrid) }

    var selectedCell by remember { mutableStateOf<Pair<Int, Int>?>(null) }

    var statusMessage by remember { mutableStateOf("") }

    val solutionGrid =
        remember(size) {
            if (size == 4) solution4x4 else solution9x9
        }

    val onNumberSelected: (Int) -> Unit = { number ->
        selectedCell?.let { (r, c) ->
            val index = r * size + c
            if (index in gridState.indices) {
                gridState =
                    gridState.toMutableList().apply {
                        this[index] = number
                    }
                statusMessage = ""
            }
        }
    }

    val onCheckGrid: () -> Unit = {
        if (gridState == solutionGrid) {
            statusMessage = "¡Correcto! Sudoku resuelto."
        } else {
            statusMessage = "Aún hay errores. ¡Sigue intentándolo!"
        }
    }

    val onRestart: () -> Unit = {
        gridState = initialGrid
        selectedCell = null
        statusMessage = "Juego Reiniciado."
    }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Sudoku ${size}x$size",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = statusMessage,
            color = if (statusMessage.contains("Correcto")) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
            modifier = Modifier.height(20.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        SudokuGrid(
            size = size,
            gridState = gridState,
            initialGrid = initialGrid,
            selectedCell = selectedCell,
            onCellSelected = { r, c -> selectedCell = Pair(r, c) },
        )

        Spacer(modifier = Modifier.height(16.dp))

        val numberRange = 1..9
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min),
            horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        ) {
            numberRange.forEach { number ->
                NumberSquareButton(
                    number = number,
                    onClick = { onNumberSelected(number) },
                    modifier = Modifier.weight(1f),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            PrimaryButton(
                text = "Revisar",
                onClick = onCheckGrid,
                modifier = Modifier.weight(1f).padding(8.dp),
            )
            PrimaryButton(
                text = "Reiniciar",
                onClick = onRestart,
                modifier = Modifier.weight(1f).padding(8.dp),
            )
        }

        PrimaryButton(
            onClick = onBackToHome,
            text = "Cambiar Tamaño (Volver a Inicio)",
        )
    }
}
