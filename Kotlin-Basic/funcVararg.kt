class FunVararg {
   companion object{
    @JvmStatic
    fun main(args: Array<String>) {
        
        println("Sum of 1, 2, 3: ${sum(1, 2, 3,4,5,6)}")
    }

    fun sum(vararg numbers: Int): Int {
        var total = 0
        for (n in numbers) {
            total += n
        }
        return total
    }
   }
}