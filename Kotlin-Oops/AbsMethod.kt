abstract class Animal {
    abstract fun sound()

    fun eat() {
        println("Animal is eating")
    }
}

class Dog : Animal() {
    override fun sound() {
        println("Woof")
    }
}

fun main() {
    val myDog = Dog()
    myDog.sound()
    myDog.eat()
}