package io.ankburov.codewars

import io.ankburov.codewars.Player.*
import org.junit.Assert.*
import org.junit.Test

class BoardModelTest {

    @Test
    fun setCell() {
        val board = BoardModel(3)

        val isSet = board.setCell(1, 1, X)

        assertTrue(isSet)
    }

    @Test
    fun getCell() {
        val board = BoardModel(3)
        val row = 1
        val column = 1
        val player = X

        val isSet = board.setCell(row, column, player)

        assertTrue(isSet)

        assertEquals(player, board.getCell(row, column))
    }

    @Test
    fun calculateNoWinner() {
        val board = BoardModel(3)

        assertNull(board.calculateWinner())
    }

    @Test
    fun calculateWinner() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, X, X),
                        mutableListOf<Player?>(X, O, X),
                        mutableListOf<Player?>(O, X, O)
                )
        )

        assertEquals(X, board.calculateWinner())
    }

    @Test
    fun calculateWinnerTopRight() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, X, O),
                        mutableListOf<Player?>(O, O, X),
                        mutableListOf<Player?>(O, X, O)
                )
        )

        assertEquals(O, board.calculateWinner())
    }

    @Test
    fun copyOf() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, X, X),
                        mutableListOf<Player?>(X, O, X),
                        mutableListOf<Player?>(O, X, O)
                )
        )

        assertEquals(board, board.copyOf())
    }

    @Test
    fun isFull() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, X, X),
                        mutableListOf<Player?>(X, O, X),
                        mutableListOf<Player?>(O, X, O)
                )
        )

        assertTrue(board.isFull())
    }

    @Test
    fun getEmptyCells() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(null, X, X),
                        mutableListOf<Player?>(X, O, X),
                        mutableListOf<Player?>(O, null, O)
                )
        )

        assertEquals(2, board.getEmptyCells().size)

    }

    @Test
    fun getEmptyCellsAroundFilledCells() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, null, null, null),
                        mutableListOf<Player?>(null, null, null, null),
                        mutableListOf<Player?>(null, null, null, null),
                        mutableListOf<Player?>(null, null, null, null)
                )
        )

        println(board.getEmptyCellsAroundFilledCells())
    }

    @Test
    fun printTest() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, X, X),
                        mutableListOf<Player?>(X, null, X),
                        mutableListOf<Player?>(O, X, O)
                )
        )

        println(board)
    }

    @Test
    fun getAdjacentCells() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, X, X),
                        mutableListOf<Player?>(X, O, X),
                        mutableListOf<Player?>(O, X, O)
                )
        )

        val adjacentCells = board.getAdjacentCells(0, 0)

        assertEquals(3, adjacentCells.size)
        assertTrue(adjacentCells.contains(0 to 1))
        assertTrue(adjacentCells.contains(1 to 0))
        assertTrue(adjacentCells.contains(1 to 1))
    }
}
