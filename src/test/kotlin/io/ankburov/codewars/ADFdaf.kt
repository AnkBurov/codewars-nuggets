package io.ankburov.codewars

fun main(args: Array<String>) {

    some { println(it) }
}

fun some(lambda: (String) -> Unit) {
    lambda("ad")
}