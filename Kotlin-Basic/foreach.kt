class ForEach{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val numbers = listOf(1, 2, 3, 4, 5)
            numbers.forEach { number ->
                println("Number: $number")
            }

            numbers.forEachIndexed { index, value ->
                    println("$index -> $value")
                }
            
            test()
        }


            fun test() {
                for (i in 1..5) {
                    if (i == 3) return
                    println(i)
                }
            }

    }
}