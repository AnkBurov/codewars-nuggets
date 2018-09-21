package io.ankburov.codewars

import kotlinx.coroutines.experimental.asCoroutineDispatcher
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlin.coroutines.experimental.CoroutineContext

fun main(args: Array<String>) {
    val executorService = Executors.newFixedThreadPool(100000)
    val coroutineDispatcher = executorService.asCoroutineDispatcher()

    runBlocking {
        val list = List(100000) {
            launch(coroutineDispatcher) {
                delay(1000)
                someWait()
//                println(Thread.currentThread().name)
                print(".")
            }
        }
        list.forEach { it.join() }
    }
}

private suspend fun someWait() {
    TimeUnit.SECONDS.sleep(1)
}