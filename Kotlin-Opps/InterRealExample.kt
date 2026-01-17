interface Payment {
    fun pay(amount: Double)
}

class GooglePay : Payment {
    override fun pay(amount: Double) {
        println("Paid ₹$amount using Google Pay")
    }
}

class PhonePe : Payment {
    override fun pay(amount: Double) {
        println("Paid ₹$amount using PhonePe")
    }
}
fun main() {
    val googlePay = GooglePay()
    googlePay.pay(1500.0)

    val phonePe  = PhonePe()
    phonePe.pay(2500.0)
}