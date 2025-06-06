//variables in kotlin
1- mutable //can be reassigned
2- immutable //cannot be reassigned

mutable variables
var name = "John"
name = "Doe" // reassigning the variable

// immutable variables
val age = 30
// age = 31 // this will cause an error

immutable variables take their value once they are assigned some value at runtime and cannot be changed later

kotlin basic types -> boolean,number, char, string, array
numbers -> int, long, short, byte, double, float

variable declaration in kotlin
var | val variableName: type = value
// example
var name: String = "John"
var age: Int = 30
var isMarried: Boolean = false
var height: Double = 5.9


hard keywords in kotlin
// hard keywords are the keywords that cannot be used as variable names

soft keywords in kotlin
// soft keywords are the keywords that can be used as variable names
// example


var x = 5 ==> this variable data type is inferred as int (type inference) no need to specify the type :Int

var x= 100L ->long
var x= 100.0 -> double
var x= 100.0f -> float
var x = 0x100 -> hexadecimal
var x = 0b100 -> binary
var x = 0o100 -> octal ->not supported in kotlin xxxxxxxxxxxxxxxxxxxxxxxxxxxx

== , != to compare contents -> both variables point to different objects in memory but have the same value
===, !== to compare references -> both references point to the same object in memory

loops
// for loop
for (i in 1..10) {
    println(i)
}
// while loop
var i = 1
while (i <= 10) {
    println(i)
    i++
}
// do while loop
var i = 1
do {
    println(i)
    i++
} while (i <= 10)
// when expression
var x = 10
when (x) {
    1 -> println("x is 1")
    2 -> println("x is 2")
    3 -> println("x is 3")
    else -> println("x is not 1, 2 or 3")
}

// when expression with range
var x = 10
when (x) {
    in 1..10 -> println("x is between 1 and 10")
    in 11..20 -> println("x is between 11 and 20")
    else -> println("x is not between 1 and 20")
}
// when expression with type
var x: Any = "Hello"
when (x) {
    is String -> println("x is a string")
    is Int -> println("x is an int")
    is Double -> println("x is a double")
    else -> println("x is not a string, int or double")
}
// when expression with multiple conditions
var x = 10
when (x) {
    1, 2, 3 -> println("x is 1, 2 or 3")
    in 4..10 -> println("x is between 4 and 10")
    else -> println("x is not between 1 and 10")
}
// when expression with lambda
var x = 10
when {
    x < 10 -> println("x is less than 10")
    x > 10 -> println("x is greater than 10")
    else -> println("x is equal to 10")
}


