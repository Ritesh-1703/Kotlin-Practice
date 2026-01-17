open class Animal {
    open fun sound() {
        println("Animal sound")
    }
}

class Dog : Animal() {
    override fun sound() {
        super.sound()
        println("Dog barks")
    }
}
fun main() {
    val dog = Dog()
    dog.sound()
}