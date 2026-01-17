data class Product(val name: String, val price: Double)

class Cart {
    private val products = mutableListOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun totalPrice(): Double {
        return products.sumOf { it.price }
    }
}

fun main() {
    val cart = Cart()
    cart.addProduct(Product("Laptop", 60000.0))
    cart.addProduct(Product("Mouse", 500.0))
    println("Total: ₹${cart.totalPrice()}")
}
