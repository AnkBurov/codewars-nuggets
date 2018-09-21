package io.ankburov.codewars

import java.util.*

fun main(args : Array<String>) {
    val input = Scanner(System.`in`)
    val expression = input.next()

    val startingBrackets = listOf("{", "[", "(")
    val endingBrackets = listOf("}", "]", ")")
    val bracketStack = Stack<String>()
    val result = expression.toCharArray()
            .asSequence()
            .map(Char::toString)
            .filter { startingBrackets.contains(it) || endingBrackets.contains(it) }
            .fold(State.INITIAL) { acc, symbol ->
                if (acc == State.INVALID) {
                    acc
                } else if (startingBrackets.contains(symbol)) {
                    bracketStack.push(symbol)
                    State.TOUCHED
                } else if (endingBrackets.contains(symbol)) {
                    if (bracketStack.isEmpty()) {
                        return@fold State.INVALID
                    }
                    val popped = bracketStack.pop()
                    when {
                        popped == "{" && symbol == "}" -> return@fold State.VALID
                        popped == "[" && symbol == "]" -> return@fold State.VALID
                        popped == "(" && symbol == ")" -> return@fold State.VALID
                        else -> return@fold State.INVALID
                    }
                } else {
                    acc
                }
            }

    println(result == State.INITIAL || result == State.VALID && bracketStack.isEmpty())
}

enum class State {
    INITIAL,
    INVALID,
    VALID,
    TOUCHED
}