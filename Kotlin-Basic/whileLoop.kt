class WhileLoop{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            var i = 1
            while(i <= 10){
                println("Value of i: $i")
                i++
            }

            var i = 10
            do {
                println(i)
                i++
            } while (i < 5)

        }
    }
}