package com.app.testapi.presentation.screens.game

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.testapi.domain.model.Difficulty
import com.app.testapi.domain.usecase.GetSudokuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.sqrt

@HiltViewModel
class GameViewModel
    @Inject
    constructor(
        private val getSudokuUseCase: GetSudokuUseCase,
        savedStateHandle: SavedStateHandle,
    ) : ViewModel() {
        private val gridSize: Int = checkNotNull(savedStateHandle["size"])

        private val _uiState = MutableStateFlow(GameUiState())
        val uiState = _uiState.asStateFlow()

        init {
            val boxSize = sqrt(gridSize.toDouble()).toInt()
            fetchNewGame(width = boxSize, height = boxSize)
        }

        fun fetchNewGame(
            difficulty: Difficulty = Difficulty.MEDIUM,
            width: Int,
            height: Int,
        ) {
            viewModelScope.launch {
                _uiState.update {
                    it.copy(isLoading = true)
                }

                getSudokuUseCase(width, height, difficulty)
                    .onSuccess { game ->
                        val flatInitial = game.initialBoard.flatten().map { it ?: 0 }
                        val flatSolution = game.solvedBoard.flatten()

                        val size = game.initialBoard.size

                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                size = size,
                                currentGrid = flatInitial,
                                initialGrid = flatInitial,
                                solutionGrid = flatSolution,
                                selectedCell = null,
                                statusMessage = "Nuevo juego: ${difficulty.name}",
                            )
                        }
                    }.onFailure { error ->
                        _uiState.update {
                            it.copy(isLoading = false, error = error.message ?: "Error desconocido")
                        }
                    }
            }
        }

        fun selectCell(
            row: Int,
            col: Int,
        ) {
            _uiState.update {
                it.copy(selectedCell = row to col)
            }
        }

        fun onNumberSelected(number: Int) {
            val currentState = _uiState.value
            val (cellR, cellC) = currentState.selectedCell ?: return

            val index = (cellR * currentState.size) + cellC

            if (index in currentState.initialGrid.indices && currentState.initialGrid[index] != 0) {
                _uiState.update { it.copy(statusMessage = "No puedes modificar una celda fija") }
                return
            }

            _uiState.update { state ->
                if (index !in state.currentGrid.indices) return@update state

                val updatedGrid =
                    state.currentGrid.toMutableList().apply {
                        this[index] = number
                    }

                val newMessage = if (!updatedGrid.contains(0)) "Tablero lleno, ¡verifica!" else ""

                state.copy(
                    currentGrid = updatedGrid,
                    statusMessage = newMessage,
                )
            }
        }

        fun checkGrid() {
            val state = _uiState.value
            val isCorrect = state.currentGrid == state.solutionGrid

            _uiState.update {
                it.copy(
                    statusMessage =
                        if (isCorrect) {
                            "¡Felicidades! Sudoku resuelto correctamente."
                        } else {
                            "Hay errores en el tablero. Revisa tus números."
                        },
                )
            }
        }

        fun restart() {
            val currentDifficulty = _uiState.value.difficulty

            val boxSize = sqrt(gridSize.toDouble()).toInt()

            fetchNewGame(
                difficulty = currentDifficulty,
                width = boxSize,
                height = boxSize,
            )
        }
    }
