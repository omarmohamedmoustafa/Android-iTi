package com.example.day5

fun main() {

    val add = { num1: Int, num2: Int -> num1 + num2 }
    val sub = { num1: Int, num2: Int -> num1 - num2 }
    val mul = { num1: Int, num2: Int -> num1 * num2 }
    val div = { num1: Int, num2: Int ->
        if (num2 == 0) {
            0
        } else {
            num1 / num2
        }
    }

    fun calculateUsingLambda(num1: Int, num2: Int, equation: (Int, Int) -> Int): Int {
        return equation(num1, num2)
    }

    // Test calculations
    println("Addition: ${calculateUsingLambda(1, 2, add)}")
    println("Subtraction: ${calculateUsingLambda(5, 3, sub)}")
    println("Multiplication: ${calculateUsingLambda(4, 3, mul)}")
    println("Division (normal): ${calculateUsingLambda(10, 2, div)}")
    println("Division by zero: ${calculateUsingLambda(10, 0, div)}")
}