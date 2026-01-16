class HigherOrder{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            val sumResult = calculate(10, 5) { a, b -> a + b }
            val multiplyResult = calculate(10, 5) { a, b -> a * b }

            println("Sum of 10 and 5 is: $sumResult")
            println("Multiplication of 10 and 5 is: $multiplyResult")
        }

        fun calculate(x: Int, y: Int, operation: (Int, Int) -> Int): Int {
            return operation(x, y)
        }
    }
}