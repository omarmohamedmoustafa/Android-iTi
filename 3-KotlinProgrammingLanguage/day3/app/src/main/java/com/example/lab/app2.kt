package com.example.lab

interface NumberSeries {
    var start: Int
    var current: Int

    fun reset()
    fun getNext(): Int
}
class ByThree : NumberSeries {
    override var current: Int = 0

    override var start: Int = 0
        set(value) {
            field = value
            current = value
        }

    override fun reset() {
        current = start
    }

    override fun getNext(): Int {
        current += 3
        return current
    }
}

fun main() {
    val series: NumberSeries = ByThree()

    series.start = 10

    println(series.getNext())
    println(series.getNext())
    println(series.getNext())

    series.reset()

    println(series.getNext()) 
}
