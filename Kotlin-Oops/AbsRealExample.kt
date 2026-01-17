abstract class BankAccount(val name: String, val balance: Double) {
    abstract fun calculateInterest(): Double

    fun displayBalance() {
        println("Balance: ₹$balance")
    }
}

class SavingAccount(name: String, balance: Double) 
    : BankAccount(name, balance) {

    override fun calculateInterest(): Double {
        return balance * 0.04
    }
}

class CurrentAccount(name: String, balance: Double) 
    : BankAccount(name, balance) {

    override fun calculateInterest(): Double {
        return balance * 0.02
    }
}

fun main() {
    val savingAcc = SavingAccount("Rohit", 5000.0)
    val currentAcc = CurrentAccount("Virat", 10000.0)

    savingAcc.displayBalance()
    println("Saving Account Interest: ₹${savingAcc.calculateInterest()}")

    currentAcc.displayBalance()
    println("Current Account Interest: ₹${currentAcc.calculateInterest()}")
}