package io.ankburov.codewars

import kotlinx.coroutines.experimental.async
import java.util.concurrent.ThreadLocalRandom
import kotlin.math.max
import kotlin.math.min

object Minimax {

    suspend fun getBestMove(board: BoardModel, forPlayer: Player): Cell? {
        val scoredMoves = board.getEmptyCellsAroundFilledCells()
                .map { emptyCell ->
                    async {
                        val newBoard = board.copyOf()
                        newBoard.setCell(emptyCell.first, emptyCell.second, forPlayer)

                        val score = analyzeMove(newBoard, forPlayer, forPlayer.other())

                        emptyCell to score
                    }
                }
                .map { it.await() }
                .sortedBy { it.second }
        val bestScoredMove = scoredMoves.lastOrNull() ?: return null
        //filter out best moves and get random of it
        val bestScoredMoves = scoredMoves.filter { it.second == bestScoredMove.second }

        val indexOfBestRandomMove = getRandomInt(0, bestScoredMoves.size)
        return bestScoredMoves[indexOfBestRandomMove].first
    }

    fun analyzeMove(board: BoardModel, forPlayer: Player, nextPlayer: Player, depth: Int = 0): Int {
        val isTerminalScore = calculateScore(board, forPlayer, depth)
        if (isTerminalScore != null) {
            return isTerminalScore
        }

        var bestVal = if (forPlayer == nextPlayer) Int.MIN_VALUE else Int.MAX_VALUE
        for ((rowIndex, columnIndex) in board.getEmptyCellsAroundFilledCells()) {
            board.setCell(rowIndex, columnIndex, nextPlayer)

            val moveScore = analyzeMove(board, forPlayer, nextPlayer.other(), depth + 1)
            bestVal = when (forPlayer) {
                nextPlayer -> max(bestVal, moveScore)
                else -> min(bestVal, moveScore)
            }

            board.emptyCell(rowIndex, columnIndex)

            if (forPlayer == nextPlayer) {
                if (bestVal > 0) {
                    break
                }
            } else {
                if (bestVal < 0) {
                    break
                }
            }

        }
        return bestVal
    }

    fun calculateScore(board: BoardModel, player: Player, depth: Int): Int? {
        val winner = board.calculateWinner()

        return when {
            winner == player -> Int.MAX_VALUE - depth
            winner == player.other() -> Int.MAX_VALUE - 10
            board.isFull() -> 0
            else -> null
        }
    }

    private fun getRandomInt(origin: Int, bound: Int) = ThreadLocalRandom.current().nextInt(origin, bound)
}