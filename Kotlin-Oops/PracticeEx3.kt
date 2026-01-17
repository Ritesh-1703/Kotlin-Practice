open class Employee(val name: String) {
    open fun calculateSalary(): Double {
        return 0.0
    }
}

class Developer(name: String) : Employee(name) {
    override fun calculateSalary(): Double {
        return 50000.0
    }
}

class Manager(name: String) : Employee(name) {
    override fun calculateSalary(): Double {
        return 80000.0
    }
}

fun main() {
    val dev = Developer("Ram")
    val mgr = Manager("Pratik")

    println("${dev.name}'s Salary: ₹${dev.calculateSalary()}")
    println("${mgr.name}'s Salary: ₹${mgr.calculateSalary()}")
}