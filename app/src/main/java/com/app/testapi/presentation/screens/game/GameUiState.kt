package com.app.testapi.presentation.screens.game

data class GameUiState(
    val grid: List<Int> = emptyList(),
    val initialGrid: List<Int> = emptyList(),
    val selectedCell: Pair<Int, Int>? = null,
    val statusMessage: String = "",
)
