package com.example.day6

import kotlinx.coroutines.*

val jobA = GlobalScope.async {
    repeat(10)
    {
        println("${it+1} from job1")
        delay(100)
    }
}
val jobB = GlobalScope.launch {
    repeat(10)
    {
        println("${it+1} from job2")
        delay(100)
    }
}

fun main(){
    runBlocking {
        jobA.await()
        jobB.join()
        println("All jobs completed")
    }
}