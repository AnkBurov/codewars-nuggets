package io.ankburov.codewars

fun main(args: Array<String>) {

    val field = listOf(
            mutableListOf("0", ".", "0", ".", "0")
//            mutableListOf("0", ".", "0"),
//            mutableListOf("0", "0", ".")
    )

    for (row in 0 until field.size) {
        for (column in 0 until field[row].size) {
            val cell = field[row][column]
            if (cell == "0") {
                printCellAndAdjacent(field, row, column)
            }
        }
    }
}

private fun printCellAndAdjacent(field: List<List<String>>, row: Int, column: Int) {
    var rightNeighbor = "-1" to "-1"
    var checkingColumn = column + 1
    while (field.isOnBoard(row, checkingColumn)) {
        if (field[row][checkingColumn] == "0") {
            rightNeighbor = row.toString() to checkingColumn.toString()
            break
        }
        checkingColumn += 1
    }

    var bottomNeighbor = "-1" to "-1"
    var checkingRow = row + 1
    while (field.isOnBoard(checkingRow, column)) {
        if (field[checkingRow][column] == "0") {
            bottomNeighbor = checkingRow.toString() to column.toString()
            break
        }
        checkingRow += 1
    }

    val print = "$column $row ${rightNeighbor.second} ${rightNeighbor.first} ${bottomNeighbor.second} ${bottomNeighbor.first}"
    println(print)
}

private fun List<List<String>>.isOnBoard(checkingRow: Int, checkingColumn: Int) = (checkingRow >= 0 && checkingColumn >= 0
        && checkingRow <= this.size - 1 && checkingColumn <= this[checkingRow].size - 1)