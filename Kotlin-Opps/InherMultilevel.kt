open class A {
    fun showA() {
        println("A")
    }
}

open class B : A() {
    fun showB() {
        println("B")
    }
}

class C : B() {
    fun showC() {
        println("C")
    }
}
fun main() {
    val objC = C()
    objC.showA()
    objC.showB()
    objC.showC()
}