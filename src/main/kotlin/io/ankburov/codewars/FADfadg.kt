package io.ankburov.codewars

import java.nio.file.Files
import java.nio.file.Paths

fun main(args: Array<String>) {
    val path = Paths.get("sk.txt")
    val encoded = Files.readAllBytes(path)
    val skShits = String(encoded)
            .split(".")

    val nettleShits = mutableSetOf<String>()
    skShits.forEachIndexed { index, line ->
                if (line.contains("Нэттл") || line.contains("Нэтл")) {
                    for (i in -8 until 8) {
                        val newLineIndex = index + i
                        if (newLineIndex >= 0 && newLineIndex < skShits.size) {
                            nettleShits.add(skShits[newLineIndex])
                        }
                    }
                }
            }
    nettleShits.forEach(::println)
}