package com.app.testapi.data.mapper

import com.app.testapi.data.remote.dto.SudokuDto
import com.app.testapi.domain.model.SudokuModel

fun SudokuDto.toDomain(): SudokuModel =
    SudokuModel(
        initialBoard = this.puzzle,
        solvedBoard = this.solution,
    )
