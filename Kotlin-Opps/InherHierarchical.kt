open class Shape {
    fun draw() {
        println("Drawing shape")
    }
}

class Circle : Shape(){
    fun radius() {
        println("Circle radius\n")
    }
}
open class Rectangle : Shape() {
    fun area() {
        println("Rectangle area\n")
    }
}
class Square : Rectangle() {
    fun perimeter() {
        println("Square perimeter\n")
    }
}

fun main() {
    val circle = Circle()
    circle.draw()
    circle.radius()

    val rectangle = Rectangle()
    rectangle.draw()
    rectangle.area()

    val square = Square()
    square.draw()
    square.area()
    square.perimeter()
}