package com.example.day6

import kotlinx.coroutines.*




fun main(){
    runBlocking {
        val job1 = launch {
            repeat(10)
            {
                delay(1000)
                println("${it+1} from job1")
            }
        }
        val job2 = launch {
           try {
               withTimeout(10000){
                   repeat(10)
                   {
                       delay(1000)
                       println("${it+1} from job2")
                   }
               }

            } catch (e: TimeoutCancellationException) {
               this@launch.cancel()
            }
        }

    }
}