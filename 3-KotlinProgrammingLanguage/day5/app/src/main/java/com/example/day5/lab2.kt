package com.example.day5

data class Human(var name: String, var id: String, var gender: String) {
    fun displayInfo() = println("Person: $name (ID: $id, Gender: $gender)")
}

fun main() {
    val person1 = Human("mona", "123", "female").apply {
        name = "omar"
        id = "12345"
        gender = "male"
    }
    person1.displayInfo()


    val person2 = Human("Omar", "12345", "male").also {
        println("New person created:")
        it.displayInfo()
    }

    val person3 = Human("Omar", "12345","male").let {
        println("New Person Created with name ${it.name}, id ${it.id}, gender ${it.gender}")
    }

}