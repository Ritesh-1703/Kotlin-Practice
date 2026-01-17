interface Employee {
    val salary: Double
    fun calculateBonus(): Double
}

class Developer : Employee {
    override val salary = 50000.0

    override fun calculateBonus(): Double {
        return salary * 0.15
    }
}
class Manager : Employee {
    override val salary = 80000.0

    override fun calculateBonus(): Double {
        return salary * 0.25
    }
}
fun main() {
    val developer = Developer()
    println("Developer Salary: ₹${developer.salary}")
    println("Developer Bonus: ₹${developer.calculateBonus()}")

    val manager = Manager()
    println("Manager Salary: ₹${manager.salary}")
    println("Manager Bonus: ₹${manager.calculateBonus()}")
}