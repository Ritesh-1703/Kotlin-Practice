class WhenStatment{
    companion object{
        @JvmStatic
        fun main(args: Array<String>){
            val day = 3
            val dayName = when (day) {
                1 -> "Monday"
                2 -> "Tuesday"
                3 -> "Wednesday"
                4 -> "Thursday"
                5 -> "Friday"
                6 -> "Saturday"
                7 -> "Sunday"
                else -> "Invalid day"
            }
            println("Day $day is $dayName")



            val number = 2

            val result = when (number) {
                1 -> "One"
                2 -> "Two"
                3 -> "Three"
                else -> "Unknown"
            }

            println(result)


            val x = 5

            when (x) {
                0, 1 -> println("x is 0 or 1")
                in 2..10 -> println("x is between 2 and 10")
                !in 11..20 -> println("x is not between 11 and 20")
                else -> println("Unknown")
            }


            val num = -10
            when {
                num > 0 -> println("Positive")
                num < 0 -> println("Negative")
                else -> println("Zero")
            }

            this.checkType("Hello")

            println(getStringLength("Kotlin"))
        }

            fun checkType(obj: Any) {
                    when (obj) {
                        is Int -> println("Integer")
                        is String -> println("String")
                        is Boolean -> println("Boolean")
                        else -> println("Unknown type")
                    }
                }

               

               fun getStringLength(obj: Any): Int {
                    return when (obj) {
                        is String -> obj.length // Smart cast to String
                        else -> 0
                    }
                }

    }
}