class Function{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            println("Hello, Kotlin Functions!")

            greet("Kotlin")

            add(5, 10)
            multiply(4, 6)
            newGreet()
            newGreet("Rohit")
            userInfo("Dhoni",7)
        }

        fun greet(name: String): String {
            return "Hello, $name!"
        }

        fun add(a: Int, b: Int): Int {
            return a + b
        }

        fun multiply(a: Int, b: Int): Int = a * b

        fun newGreet(name: String ="Guest") {
        
            println("Welcome, $name!")
        }

        fun userInfo(name: String, age: Int) {
            println("Name: $name, Age: $age")
        }
    }
}