abstract class BankAccount(private var balance: Double) {

    fun deposit(amount: Double) {
        balance += amount
    }

    fun getBalance(): Double {
        return balance
    }

    abstract fun calculateInterest(): Double
}

class SavingsAccount(balance: Double) : BankAccount(balance) {
    override fun calculateInterest(): Double {
        return getBalance() * 0.04
    }
}

class CurrentAccount(balance: Double) : BankAccount(balance) {
    override fun calculateInterest(): Double {
        return 0.0
    }
}

fun main() {
    val savings = SavingsAccount(1000.0)
    savings.deposit(500.0)
    println("Savings Account Balance: ₹${savings.getBalance()}")
    println("Savings Account Interest: ₹${savings.calculateInterest()}")

    val current = CurrentAccount(2000.0)
    current.deposit(1000.0)
    println("Current Account Balance: ₹${current.getBalance()}")
    println("Current Account Interest: ₹${current.calculateInterest()}")
}