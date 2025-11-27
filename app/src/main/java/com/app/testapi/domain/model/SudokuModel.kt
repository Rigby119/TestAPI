package com.app.testapi.domain.model

data class SudokuModel(
    val initialBoard: List<List<Int?>>,
    val solvedBoard: List<List<Int>>,
)
