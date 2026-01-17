open class Person(val name: String) {
    init {
        println("Name: $name")
    }
}

class Employee(name: String, val employeeId: Int) : Person(name) {
    init {
        println("Employee ID: $employeeId")
    }
}
fun main() {
    val employee = Employee("Rohit", 101)
}