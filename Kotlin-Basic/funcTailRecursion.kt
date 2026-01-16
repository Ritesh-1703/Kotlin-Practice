class TailRecursion{
    companion object{
        @JvmStatic
        fun main(args: Array<String>) {
            println(factorial(5))
        }

        tailrec fun factorial(n: Int, res: Int = 1): Int {
            return if (n <= 1) res else factorial(n - 1, n * res)
        }
    }
}