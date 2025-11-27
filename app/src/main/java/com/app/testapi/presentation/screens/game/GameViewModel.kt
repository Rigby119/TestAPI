package com.app.testapi.presentation.screens.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GameViewModel
    @Inject
    constructor(
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val size = savedStateHandle.get<Int>("size") ?: 9
        private val initialGrid =
            if (size == 4) initial4x4Grid else initial9x9Grid

        private val solutionGrid =
            if (size == 4) solution4x4 else solution9x9

        private val _uiState =
            MutableStateFlow(
                GameUiState(
                    grid = initialGrid,
                    initialGrid = initialGrid,
                    selectedCell = null,
                    statusMessage = "",
                ),
            )
        val uiState: StateFlow<GameUiState> = _uiState

        fun selectCell(
            row: Int,
            col: Int,
        ) {
            _uiState.update {
                it.copy(selectedCell = row to col)
            }
        }

        fun onNumberSelected(number: Int) {
            val (cellR, cellC) = _uiState.value.selectedCell ?: return
            val index = cellR * size + cellC

            _uiState.update { state ->
                if (index !in state.grid.indices) return

                val updatedGrid =
                    state.grid.toMutableList().apply {
                        this[index] = number
                    }

                state.copy(
                    grid = updatedGrid,
                    statusMessage = "",
                )
            }
        }

        fun checkGrid() {
            val isCorrect = _uiState.value.grid == solutionGrid
            _uiState.update {
                it.copy(
                    statusMessage =
                        if (isCorrect) {
                            "¡Correcto! Sudoku resuelto."
                        } else {
                            "Aún hay errores. ¡Sigue intentándolo!"
                        },
                )
            }
        }

        fun restart() {
            _uiState.update {
                it.copy(
                    grid = initialGrid,
                    selectedCell = null,
                    statusMessage = "Juego Reiniciado.",
                )
            }
        }
    }

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
