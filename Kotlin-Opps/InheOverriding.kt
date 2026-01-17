open class Animal {
    open fun sound() {
        println("Animal makes a sound")
    }
}

class Dog : Animal() {
    override fun sound() {
        println("Dog barks")
    }

}

fun main() {
    val dog = Dog()
    dog.sound()
}
