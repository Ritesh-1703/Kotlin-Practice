class User{
    lateinit var name: String

    fun initializeName(name: String) {
        this.name = name
    }

    fun printName() {
        if (::name.isInitialized) {
            println("User name is: $name")
        } else {
            println("Name is not initialized")
        }
    }
}
fun main() {
    val user = User()
    user.printName() // Output: Name is not initialized
    user.initializeName("Alice")
    user.printName() // Output: User name is: Alice
}