interface Payment {
    fun pay(amount: Double)
}

class GooglePay : Payment {
    override fun pay(amount: Double) {
        println("Paid ₹$amount via Google Pay")
    }
}

class CreditCard : Payment {
    override fun pay(amount: Double) {
        println("Paid ₹$amount via Credit Card")
    }
}

fun makePayment(payment: Payment) {
    payment.pay(500.0)
}

fun main() {
    val googlePay = GooglePay()
    val creditCard = CreditCard()

    makePayment(googlePay)
    makePayment(creditCard)
}