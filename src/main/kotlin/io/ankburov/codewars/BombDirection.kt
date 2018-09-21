package io.ankburov.codewars

import java.util.*
import java.util.function.BiPredicate

fun main(args: Array<String>) {
    //todo избавиться от поля и оставить только по мапе обращение на основании
    //todo чтобы не заполнять весь массив достаточно нули там хранить - норм тема
    //todo деление пополам и чисто математическая операция
    val building = Building(width = 9999, height = 9999)

    building.editPossibleBombLocations(54 to 77, BombDirection.DR)
    println(building.getMiddlePositionOfPossibleBombLocations())

}

class Building(private val width: Int, private val height: Int) {
    private val field: Array<Array<Window?>> = Array(width) { _ -> Array<Window?>(height) { _ -> Window() } }

    fun editPossibleBombLocations(start: Pair<Int, Int>, bombDirection: BombDirection) {
        field.forEachIndexed { rowIndex, windowColumns ->
            windowColumns.forEachIndexed { columnIndex, window ->
                if (window?.possibleBomb == true && !bombDirection.isCheckInDirectionFromStart(start, rowIndex to columnIndex)) {
                    window.possibleBomb = false
                }
            }
        }
    }

    fun getMiddlePositionOfPossibleBombLocations(): Pair<Int, Int> {
        val (start, end) = getBombRectangular()
        val x = (start.first + end.first) / 2
        val y = (start.second + end.second) / 2
        return x to y
    }

    private fun getBombRectangular(): BombRectangular {
        var start: Pair<Int, Int>? = null
        loop@ for (rowIndex in 0 until field.size) {
            for (columnIndex in 0 until field[rowIndex].size) {
                if (field[rowIndex][columnIndex]?.possibleBomb == true) {
                    start = rowIndex to columnIndex
                    break@loop
                }
            }
        }
        if (start == null) start = 0 to 0

        var end: Pair<Int, Int>? = null
        for (rowIndex in start.first until field.size) {
            for (columnIndex in start.second until field[rowIndex].size) {
                if (field[rowIndex][columnIndex]?.possibleBomb == true) {
                    end = rowIndex to columnIndex
                }
            }
        }
        if (end == null) end = width - 1 to height - 1

        return BombRectangular(start, end)
    }
}

data class BombRectangular(val start: Pair<Int, Int>, val end: Pair<Int, Int>)

class Window(var possibleBomb: Boolean = true)

// first is X, second is Y
enum class BombDirection(private val isInTheDirection: DirectionPredicate) {
    U(DirectionPredicate { start, check -> check.second - start.second < 0 }),
    D(DirectionPredicate { start, check -> check.second - start.second > 0 }),
    L(DirectionPredicate { start, check -> check.first - start.first < 0 }),
    R(DirectionPredicate { start, check -> check.first - start.first > 0 }),
    UR(DirectionPredicate { start, check -> check.second - start.second < 0 && check.first - start.first > 0 }),
    UL(DirectionPredicate { start, check -> check.second - start.second < 0 && check.first - start.first < 0 }),
    DR(DirectionPredicate { start, check -> check.second - start.second > 0 && check.first - start.first > 0 }),
    DL(DirectionPredicate { start, check -> check.second - start.second > 0 && check.first - start.first < 0 });

    fun isCheckInDirectionFromStart(start: Pair<Int, Int>, check: Pair<Int, Int>): Boolean =
            isInTheDirection.test(start, check)
}

typealias DirectionPredicate = BiPredicate<Pair<Int, Int>, Pair<Int, Int>>