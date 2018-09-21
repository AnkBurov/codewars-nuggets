package io.ankburov.codewars

import io.ankburov.codewars.Player.O
import io.ankburov.codewars.Player.X
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class MinimaxTest {

    /*@Test
    fun calculateScore() {
        val board = BoardModel(3, 3,
                listOf(
                        mutableListOf<Player?>(X, X, X),
                        mutableListOf<Player?>(X, O, X),
                        mutableListOf<Player?>(O, X, O)
                )
        )

        assertEquals(10, Minimax.calculateScore(Cell(0, 0), board, X).score)
        assertEquals(-10, Minimax.calculateScore(Cell(0, 0), board, O).score)
    }*/

    @Test
    fun calculateMinimax() {
        runBlocking {
            val board = BoardModel(3, 3,
                    listOf(
                            mutableListOf<Player?>(X, X, null),
                            mutableListOf<Player?>(X, O, X),
                            mutableListOf<Player?>(O, X, O)
                    )
            )

            assertEquals(Cell(0, 2), Minimax.getBestMove(board, O))
        }
    }

    @Test
    fun testMinimax2() {
        runBlocking {
            val board = BoardModel(3, 3,
                    listOf(
                            mutableListOf<Player?>(X, null, X),
                            mutableListOf<Player?>(X, O, null),
                            mutableListOf<Player?>(O, X, O)
                    )
            )

            assertEquals(Cell(0, 1), Minimax.getBestMove(board, O))
        }
    }

    @Test
    fun testMinimax3() {
        runBlocking {
            val board = BoardModel(3, 3,
                    listOf(
                            /*mutableListOf<Player?>(X, null, null),
                            mutableListOf<Player?>(null, O, null),
                            mutableListOf<Player?>(X, null, null)*/

                            /*Ошибочный минимакс, когда больше одной вложенности ему срывает башню
                            * mutableListOf<Player?>(X, null, null),
                            mutableListOf<Player?>(null, O, X),
                            mutableListOf<Player?>(X, X, O)*/

                            mutableListOf<Player?>(X, null, null),
                            mutableListOf<Player?>(null, O, X),
                            mutableListOf<Player?>(X, X, O)
                    )
            )

            assertEquals(Cell(1, 0), Minimax.getBestMove(board, O))
        }
    }

    @Test
    fun testMinimax4() {
        runBlocking {
            val board = BoardModel(3, 3,

                    /*проигрышная позиция
                    * listOf(
                            mutableListOf<Player?>(X, O, null),
                            mutableListOf<Player?>(null, null, null),
                            mutableListOf<Player?>(X, null, null)
                    )*/

                    // почему 1, 1 - наилучшая позиция есть наихудшая? score?
                    listOf(
                            mutableListOf<Player?>(X, null, null),
                            mutableListOf<Player?>(null, null, null),
                            mutableListOf<Player?>(null, null, null)
                    )
            )

            assertEquals(Cell(1, 1), Minimax.getBestMove(board, O))
        }
    }

    @Test
    fun testMinimax5() {
        runBlocking {
            val board = BoardModel(3, 3,
                    listOf(
                            mutableListOf<Player?>(X, null, null, null),
                            mutableListOf<Player?>(null, null, null, null),
                            mutableListOf<Player?>(null, null, null, null),
                            mutableListOf<Player?>(null, null, null, null)
                    )
            )

            println(Minimax.getBestMove(board, O))
        }
    }

    @Test
    fun testMinimax6() {
        runBlocking {
            val board = BoardModel(4, 3,
                    listOf(
                            mutableListOf<Player?>(X, null, null, null),
                            mutableListOf<Player?>(null, null, null, null),
                            mutableListOf<Player?>(null, null, null, null),
                            mutableListOf<Player?>(null, null, null, null)
                    )
            )

            println(Minimax.getBestMove(board, O))
        }
    }

    @Test
    fun testMinimax7() {
        runBlocking {
            val board = BoardModel(6)

            println(Minimax.getBestMove(board, O))
        }
    }
}