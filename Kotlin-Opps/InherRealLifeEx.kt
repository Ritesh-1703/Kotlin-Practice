open class Employee(val name: String, val salary: Double) {
    open fun calculateBonus(): Double {
        return salary * 0.10
    }
}

class Manager(name: String, salary: Double) : Employee(name, salary) {
    override fun calculateBonus(): Double {
        return salary * 0.20
    }
}
class Developer(name: String, salary: Double) : Employee(name, salary) {
    override fun calculateBonus(): Double {
        return salary * 0.15
    }
}

fun main() {
    val emp = Employee("Rohit", 50000.0)
    println("Employee: ${emp.name}, Salary: ${emp.salary}, Bonus: ${emp.calculateBonus()}")

    val mgr = Manager("Dhoni", 80000.0)
    println("Manager: ${mgr.name}, Salary: ${mgr.salary}, Bonus: ${mgr.calculateBonus()}")

    val dev = Developer("Virat", 70000.0)
    println("Developer: ${dev.name}, Salary: ${dev.salary}, Bonus: ${dev.calculateBonus()}")
}