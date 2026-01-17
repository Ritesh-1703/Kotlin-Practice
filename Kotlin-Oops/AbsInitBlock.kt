abstract class Shape {
    init {
        println("Shape created")
    }

    abstract fun draw()
}

class Circle : Shape() {
    override fun draw() {
        println("Drawing Circle")
    }
}
class Rectangle : Shape() {
    override fun draw() {
        println("Drawing Rectangle")
    }
}

fun main() {
    val circle = Circle()
    val rectangle = Rectangle()
    circle.draw()
    rectangle.draw()
}