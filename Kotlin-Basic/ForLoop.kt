class ForLoop{
    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            for(i in 1..5){
                println("Value of i: $i")
            }


            for(i in 1 until 5){
                println("Value of i using until: $i")
        }


        for(i in 5 downTo 1){
            println("Value of i using downTo: $i")
        }

        for(i in 1..10 step 2){
            println("Value of i using step: $i")
        }

        val numbers = arrayOf(10, 20, 30, 40, 50)
        for(num in numbers){
            println("Array element: $num")
        }

        val fruits = listOf("Apple", "Banana", "Cherry")

        for(i in fruits.indices){
            println("Fruit at index $i: ${fruits[i]}")
        }

        for((index, value) in fruits.withIndex()){
            println("Fruit at index $index: $value")
        }
        }
    }
}