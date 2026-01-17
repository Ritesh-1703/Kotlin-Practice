open class A {
    fun showA() {
        println("Class A")
    }
}

class B : A() {
    fun showB() {
        println("Class B")
    }
}
fun main() {
    val objB = B()
    objB.showA()
    objB.showB()
}