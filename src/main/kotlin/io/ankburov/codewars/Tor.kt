package io.ankburov.codewars

import java.util.*
import java.util.concurrent.LinkedBlockingQueue

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 * ---
 * Hint: You can use the debug stream to print initialTX and initialTY, if Thor seems not follow your orders.
 **/
fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    val lightX = input.nextInt() // the X position of the light of power
    val lightY = input.nextInt() // the Y position of the light of power
    val initialTX = input.nextInt() // Thor's starting X position
    val initialTY = input.nextInt() // Thor's starting Y position

    val bestMovesQueue = getMoveQueue(initialTX, initialTY, lightX, lightY)
    // game loop
    while (true) {
        // A single line providing the move to be made: N NE E SE S SW W or NW
        bestMovesQueue.poll()?.let { println(it.name) }
    }
}

fun getMoveQueue(initialX: Int, initialY: Int, goalX: Int, goalY: Int): Queue<Step> {
    val stepQueue = LinkedBlockingQueue<Step>()

    var currentX = initialX
    var currentY = initialY

    while (currentX != goalX || currentY != goalY) {
        val (bestStep, bestMovePoint) = Step.values()
                .asSequence()
                .map { it to it.makeImaginaryMove(currentX, currentY) }
                .minBy {
                    val pointX = it.second.first
                    val pointY = it.second.second

                    Math.sqrt(Math.pow((goalX - pointX).toDouble(), 2.0) + Math.pow((goalY - pointY).toDouble(), 2.0))
//                    Math.abs(goalX - pointX) + Math.abs(goalY - pointY)
                }!!

        stepQueue.add(bestStep)
        currentX = bestMovePoint.first
        currentY = bestMovePoint.second
    }
    return stepQueue
}

enum class Step(private val xChange: Int, private val yChange: Int) {
    N(0, -1),
//    NE(1, -1),
    E(1, 0),
//    SE(1, 1),
    S(0, 1),
//    SW(-1, 1),
    W(-1, 0);
//    NW(-1, -1);

    fun makeImaginaryMove(currentX: Int, currentY: Int): Point {
        return currentX + xChange to currentY + yChange
    }
}

typealias Point = Pair<Int, Int>