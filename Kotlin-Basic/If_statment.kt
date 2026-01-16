class Statment{
    companion object{
        @JvmStatic
        fun main(arga: Array<String>){
            val a =10

            if(a >0){
                println("A is positive number")
            }else{
                println("A is negative number")
            }




            if(a %2 ==0){
                println("A is even number")
            }else{
                println("A is odd number")
            }



            val marks = 75

            val grade = if (marks >= 90) {
                "A"
            } else if (marks >= 75) {
                "B"
            } else if (marks >= 50) {
                "C"
            } else {
                "F"
            }

            println("Grade: $grade")


            val n = -3
            if (n < 0) println("Negative number") else println("Non-negative number")


            val num = 15

            if (num > 0) {
                if (num % 2 == 0) {
                    println("Positive even number")
                } else {
                    println("Positive odd number")
                }
            } else {
                println("Non-positive number")
            }
            
            

        }
    }
}