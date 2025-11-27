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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.app.testapi.presentation.ui.components.atoms.NumberSquareButton
import com.app.testapi.presentation.ui.components.atoms.PrimaryButton
import com.app.testapi.presentation.ui.components.molecules.SudokuGrid

@Suppress("ktlint:standard:function-naming")
@Composable
fun GameScreen(
    onBackToHome: () -> Unit,
    viewModel: GameViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsState().value

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Sudoku",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp),
        )
        Text(
            text = state.statusMessage,
            color =
                if (state.statusMessage.contains("Correcto")) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.error
                },
            modifier = Modifier.height(20.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        SudokuGrid(
            size = state.size,
            gridState = state.currentGrid,
            initialGrid = state.initialGrid,
            selectedCell = state.selectedCell,
            onCellSelected = { r, c -> viewModel.selectCell(r, c) },
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
                    onClick = { viewModel.onNumberSelected(number) },
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
                onClick = { viewModel.checkGrid() },
                modifier = Modifier.weight(1f).padding(8.dp),
            )
            PrimaryButton(
                text = "Reiniciar",
                onClick = { viewModel.restart() },
                modifier = Modifier.weight(1f).padding(8.dp),
            )
        }

        PrimaryButton(
            onClick = onBackToHome,
            text = "Cambiar Tamaño (Volver a Inicio)",
        )
    }
}
