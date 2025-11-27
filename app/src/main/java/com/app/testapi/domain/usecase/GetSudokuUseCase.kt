package com.app.testapi.domain.usecase

import com.app.testapi.domain.model.Difficulty
import com.app.testapi.domain.model.SudokuModel
import com.app.testapi.domain.repository.SudokuRepository
import javax.inject.Inject

class GetSudokuUseCase
    @Inject
    constructor(
        private val repository: SudokuRepository,
    ) {
        suspend operator fun invoke(
            width: Int = 3,
            height: Int = 3,
            difficulty: Difficulty = Difficulty.MEDIUM,
        ): Result<SudokuModel> = repository.getNewGame(width, height, difficulty)
    }
