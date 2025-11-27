package com.app.testapi.data.repository

import com.app.testapi.data.mapper.toDomain
import com.app.testapi.data.remote.api.SudokuApi
import com.app.testapi.domain.model.Difficulty
import com.app.testapi.domain.model.SudokuModel
import com.app.testapi.domain.repository.SudokuRepository
import javax.inject.Inject

class SudokuRepositoryImpl
    @Inject
    constructor(
        private val api: SudokuApi,
    ) : SudokuRepository {
        override suspend fun getNewGame(
            width: Int,
            height: Int,
            difficulty: Difficulty,
        ): Result<SudokuModel> =
            try {
                val response =
                    api.getSudoku(
                        width = width,
                        height = height,
                        difficulty = difficulty.apiValue,
                    )
                Result.success(response.toDomain())
            } catch (e: Exception) {
                e.printStackTrace()
                Result.failure(e)
            }
    }
