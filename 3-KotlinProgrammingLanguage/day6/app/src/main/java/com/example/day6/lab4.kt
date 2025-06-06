package com.example.day6
import kotlinx.coroutines.*

suspend fun sumArrayElements(arr: Array<Int>): Int {
    var Sum: Int = 0
    for(i in arr)
    {
        Sum += i
        delay(100)

    }
    return Sum
}


fun main() = runBlocking {
    val numbers = arrayOf(1, 2, 3, 4, 5)
    val sum = sumArrayElements(numbers)
    println("Sum = $sum")
}
