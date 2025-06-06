package com.example.day1

import java.util.Random

/*Use the readline() to read inputs from the user. User should input their name, if
user enters empty string, store a default value. Use an if expression to define greeting
message based on whether the user entered a name*/
fun lab1_1(){
    println("Enter your name:")
    var defaultName = "Default Name"
    var input = readLine()
    var name = if(input.isNullOrEmpty()) defaultName else input //is nullorblank for ignoring white spaces
    var greeting = "Hello $name"
    println(greeting)
}
/* create an Array of integer. Use java.util.Random to fill the Array with 100 random
number between 1 and 100. Go through collection and print values less than or equal
to 10*/
fun lab1_2(){
    val random = Random()
    val numbers = Array(100) { random.nextInt(101) }

    println("Numbers less than or equal to 10:")
    for (number in numbers) {
        if (number <= 10) {
            println(number)
        }
    }
}
/*Calculator: ADD, SUB, MUL, DIV*/
fun lab1_3() {
    println("Enter mode (ADD -> 1, SUB -> 2, MUL -> 3, DIV -> 4):")
    val mode = readLine()?.uppercase()

    if (mode == null || mode !in listOf("ADD", "SUB", "MUL", "DIV", "1", "2", "3", "4")) {
        println("Invalid mode")
        return
    }

    println("Enter first number:")
    val num1 = readLine()?.toDoubleOrNull()
    println("Enter second number:")
    val num2 = readLine()?.toDoubleOrNull()

    if (num1 == null || num2 == null) {
        println("Invalid input")
        return
    }

    val result = when (mode) {
        "ADD", "1" -> num1 + num2
        "SUB", "2" -> num1 - num2
        "MUL", "3" -> num1 * num2
        "DIV", "4" -> if (num2 == 0.0) "Cannot divide by zero" else num1 / num2
        else -> "Invalid mode"
    }

    println("Result: $result")
}

fun lab1_5(){
    println("Enter your first name:")
    var fName = readLine()
    println("Enter your last name:")
    var lName = readLine()

    var result = "$fName $lName"
    println(result)
}
fun main(){
//    lab1_1()
//    lab1_2()
    lab1_3()
    lab1_5()
}