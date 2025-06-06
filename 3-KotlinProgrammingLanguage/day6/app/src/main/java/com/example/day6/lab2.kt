package com.example.day6

import kotlinx.coroutines.*

suspend fun factorial(n: Int): Int{
    if (n == 0 || n == 1) {
        return 1
    }
    delay(100)
    return n * factorial(n - 1)

}

fun main() {
    runBlocking {
        val number = 5
        val result = async {
            factorial(number)
        }
        println("Factorial of $number is ${result.await()}")

    }
}