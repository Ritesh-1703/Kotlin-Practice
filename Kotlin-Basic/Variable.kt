class Variabale{

    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            var a:Int=10
            var b:Int=20
            var sum:Int=a+b
            println("Sum is : $sum")

            val pi:Double=3.14
            val area:Double=pi*5*5
            println("Area is : $area")

            var name:String="Kotlin"
            println("Welcome to $name Programming Language")

            val isKotlinFun:Boolean=true
            println("Is Kotlin fun? : $isKotlinFun")

            val list = mutableListOf(1, 2, 3, 4, 5)
            list.add(6)
            println("List: $list")
        }
    }
    
}
