package com.example.day2
import kotlin.math.PI

class Picture {
    fun sumAreas(shape1:Shape, shape2:Shape, shape3:Shape) {
        var total = 0.0
        for (shape in listOf(shape1, shape2, shape3)) {
            val area = shape.calcArea()
            total += area
        }
        println("Total Area = $total")
    }
}

abstract class Shape {
    protected var dim: Double = 0.0
    constructor()
    constructor(dim: Double) : this() {
        this.dim = dim
    }
    abstract fun calcArea(): Double
}
 class Circle (val radius: Double): Shape(radius) {
    override fun calcArea(): Double {
        return PI * dim * dim
    }
}
 class Rectangle (val length: Double, val width: Double): Shape() {
    override fun calcArea(): Double {

        return length * width
    }
}
 class Triangle (val base: Double, val height: Double): Shape() {
    override fun calcArea(): Double {
        return 0.5 * base * height
    }
}
fun main(){
    val circle = Circle(5.0)
    val rectangle = Rectangle(4.0, 5.0)
    val triangle = Triangle(3.0, 4.0)

    val picture = Picture()
    picture.sumAreas(circle, rectangle, triangle)
}