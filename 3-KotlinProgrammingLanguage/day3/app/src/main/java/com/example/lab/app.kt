package com.example.lab

class Person {
    var name: String = ""
    var address: Address = Address()

    class Address {
        var streetName: String = ""
        var city: String = ""
        var building: Building = Building.VILLA
    }

    override fun toString(): String {
        return "$name lives in:\nStreet: ${address.streetName}\nCity: ${address.city}\nBuilding: ${address.building}"
    }
}

enum class Building {
    VILLA,
    APARTMENT
}

fun main() {
    val Omar = Person()
    Omar.name = "Omar"
    Omar.address.streetName = "Main Street"
    Omar.address.city = "Cairo"
    Omar.address.building = Building.VILLA
    println(Omar)
    println()
    println()
    val Mohamed = Person()
    Mohamed.name = "Mohamed"
    Mohamed.address.streetName = "Second Street"
    Mohamed.address.city = "Cairo"
    Mohamed.address.building = Building.APARTMENT
    println(Mohamed)
}
