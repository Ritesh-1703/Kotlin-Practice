abstract class ClassName {
    abstract fun methodName()
    fun concreteMethod() {
        println("Normal method")
    }
}
class SubClass : ClassName() {
    override fun methodName() {
        println("Implemented abstract method")
    }
}
fun main() {
    val obj = SubClass()
    obj.methodName()
    obj.concreteMethod()
}