package com.example.day6
import kotlinx.coroutines.*

fun main() {
    runBlocking {
        val job1 = launch {
            try {
                repeat(times = 1_000) { i ->
                    delay(timeMillis = 200)
                    println("Printing $i from job1")
                }
            } catch (e: CancellationException) {
                println(e)
            }
        }
        val job2 = launch {
            try {
                repeat(times = 1_000) { i ->
                    delay(timeMillis = 200)
                    println("Printing $i from job2")
                }
            } catch (e: CancellationException) {
                println(e)
            }
        }

        delay(timeMillis = 1100)
        job1.cancelAndJoin()
        println("Cancelled job1 successfully")

        job2.cancel()
        println("Cancelled job2 successfully")
        delay(timeMillis = 1000)
    }
}