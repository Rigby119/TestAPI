package com.app.testapi.presentation.ui.components.molecules

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.testapi.presentation.theme.TestAPITheme
import com.app.testapi.presentation.ui.components.atoms.CellButton
import com.app.testapi.presentation.ui.components.atoms.PrimaryButton

@Suppress("ktlint:standard:function-naming")
@Composable
fun SudokuGrid(
    size: Int,
    gridState: List<Int>,
    initialGrid: List<Int>,
    selectedCell: Pair<Int, Int>?,
    onCellSelected: (Int, Int) -> Unit,
) {
    val subGridSize =
        when (size) {
            4 -> 2
            9 -> 3
            else -> 1
        }

    Column(
        modifier =
            Modifier
                .aspectRatio(1f)
                .padding(4.dp)
                .border(2.dp, MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        repeat(subGridSize) { subRow ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .border(
                            if (subRow < subGridSize - 1) 2.dp else 0.dp,
                            MaterialTheme.colorScheme.onSurface,
                        ),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                repeat(subGridSize) { subCol ->
                    Column(
                        modifier =
                            Modifier
                                .fillMaxHeight()
                                .weight(1f)
                                .border(
                                    if (subCol < subGridSize - 1) 2.dp else 0.dp,
                                    MaterialTheme.colorScheme.onSurface,
                                ),
                        verticalArrangement = Arrangement.SpaceBetween,
                    ) {
                        repeat(subGridSize) { cellRowInSub ->
                            Row(
                                modifier =
                                    Modifier
                                        .fillMaxWidth()
                                        .weight(1f),
                                horizontalArrangement = Arrangement.SpaceBetween,
                            ) {
                                repeat(subGridSize) { cellColInSub ->
                                    val row = subRow * subGridSize + cellRowInSub
                                    val col = subCol * subGridSize + cellColInSub
                                    val index = row * size + col
                                    val number = gridState.getOrElse(index) { 0 }
                                    val isInitial = initialGrid.getOrElse(index) { 0 } != 0
                                    val isCurrentCell = selectedCell == Pair(row, col)

                                    CellButton(
                                        number = number,
                                        isInitial = isInitial,
                                        isSelected = isCurrentCell,
                                        onSelect = {
                                            if (!isInitial) {
                                                onCellSelected(row, col)
                                            }
                                        },
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
