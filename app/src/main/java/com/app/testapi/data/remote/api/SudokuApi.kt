package com.app.testapi.data.remote.api

import com.app.testapi.data.remote.dto.SudokuDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SudokuApi {
    @GET("v1/sudokugenerate")
    suspend fun getSudoku(
        @Query("width") width: Int,
        @Query("height") height: Int,
        @Query("difficulty") difficulty: String,
    ): SudokuDto
}
