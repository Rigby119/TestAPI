package com.app.testapi.presentation.screens.game

import com.app.testapi.domain.model.Difficulty

data class GameUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val difficulty: Difficulty = Difficulty.MEDIUM,
    val size: Int = 9,
    val currentGrid: List<Int> = emptyList(),
    val initialGrid: List<Int> = emptyList(),
    val solutionGrid: List<Int> = emptyList(),
    val selectedCell: Pair<Int, Int>? = null,
    val statusMessage: String = "",
) {
    val isBoardReady: Boolean
        get() = !isLoading && currentGrid.isNotEmpty()
}
