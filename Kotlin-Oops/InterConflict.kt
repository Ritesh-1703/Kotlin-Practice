interface A {
    fun show() {
        println("A show")
    }
}

interface B {
    fun show() {
        println("B show")
    }
}

class C : A, B {
    override fun show() {
        super<A>.show()
        super<B>.show()
        println("C show")
    }
}
fun main() {
    val obj = C()
    obj.show()
}