package com.app.testapi.domain.repository

import com.app.testapi.domain.model.Difficulty
import com.app.testapi.domain.model.SudokuModel

interface SudokuRepository {
    suspend fun getNewGame(
        width: Int,
        height: Int,
        difficulty: Difficulty,
    ): Result<SudokuModel>
}
