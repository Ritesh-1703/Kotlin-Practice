interface A {
    fun showA()
}

interface B {
    fun showB()
}

class C : A, B {
    override fun showA() {
        println("Show A ")
    }

    override fun showB() {
        println("Show B")
    }
}
fun main() {
    val obj = C()
    obj.showA()
    obj.showB()
}