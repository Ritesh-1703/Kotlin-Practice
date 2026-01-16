class Recursion{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val result = factorial(5)
            println("Factorial of 5 is: $result")
        }

        fun factorial(n: Int): Int {
            return if (n == 1) 1 else n * factorial(n - 1)
        }

    }
}