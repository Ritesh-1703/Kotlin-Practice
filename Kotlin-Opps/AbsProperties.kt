abstract class Employee {
    abstract val salary: Double
}

class Developer : Employee() {
    override val salary = 60000.0
}
class Manager : Employee() {
    override val salary = 80000.0
}
fun main() {
    val dev = Developer()
    val mgr = Manager()
    println("Developer Salary: ${dev.salary}")
    println("Manager Salary: ${mgr.salary}")
}